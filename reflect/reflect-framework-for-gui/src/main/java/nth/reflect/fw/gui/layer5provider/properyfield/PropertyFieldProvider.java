package nth.reflect.fw.gui.layer5provider.properyfield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.ProviderHelperNotDeclaredException;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * <p>
 * The {@link PropertyFieldProvider} is a {@link Provider} that creates
 * {@link PropertyField}s using {@link PropertyFieldFactory}s. These are
 * configured in the {@link ReflectApplication}:
 * <ul>
 * <li>{@link PropertyFieldProvider} with the
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldProviderClass()}
 * method</li>
 * <li>{@link PropertyFieldFactory}s with the
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldFactoryClasses()}
 * method</li>
 * </ul>
 * <p>
 * {@insert PropertyFieldFactories}
 * <p>
 * You can add custom fields by implementing {@link PropertyField} and
 * {@link PropertyFieldFactory} and overriding the
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldFactoryClasses()}.
 * Look at the existing implementations for inspiration.
 * <p>
 * 
 * @author nilsth
 *
 */
public class PropertyFieldProvider implements Provider {

	private final ProviderContainer container;
	private final List<PropertyFieldFactory> factories;

	public PropertyFieldProvider(ProviderContainer providerContainer) {
		this.container = providerContainer;
		factories = createFactories();
	}

	private List<PropertyFieldFactory> createFactories() {
		List<Class<? extends PropertyFieldFactory>> factoryClasses = getFactoryClasses();
		ArrayList<PropertyFieldFactory> factories = new ArrayList();
		for (Class<? extends PropertyFieldFactory> factoryClass : factoryClasses) {
			container.add(factoryClass);
			PropertyFieldFactory factory = container.get(factoryClass);
			factories.add(factory);
		}
		return Collections.unmodifiableList(factories);
	}

	private List<Class<? extends PropertyFieldFactory>> getFactoryClasses() {
		GraphicalUserInterfaceApplication application = container.get(GraphicalUserInterfaceApplication.class);
		List<Class<? extends PropertyFieldFactory>> factoryClasses = application.getPropertyFieldFactoryClasses();
		if (factoryClasses == null || factoryClasses.size() == 0) {
			String canonicalMethodName = ReflectApplication.class.getSimpleName() + ".getPropertyFieldFactoryClasses";
			throw new ProviderHelperNotDeclaredException(PropertyFieldFactory.class, canonicalMethodName);
		}
		return factoryClasses;
	}

	public PropertyFieldFactory find(FormTab formTab, PropertyValueModel propertyValueModel) {
		Optional<PropertyFieldFactory> result = factories
				.stream()
				.filter(factory -> factory.canCreate(formTab, propertyValueModel))
				.findFirst();
		return result.orElseThrow(() -> new PropertyFieldFactoryNotFoundException(propertyValueModel));
	}

	public boolean canCreate(TypeInfo typeInfo, FormTab formTab, PropertyValueModel propertyValueModel) {
		boolean canCreate = factories.stream().anyMatch(factory -> factory.canCreate(formTab, propertyValueModel));
		return canCreate;
	}

	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyFieldFactory factory = find(formTab, propertyValueModel);
		PropertyField propertyField = factory.create(formTab, propertyValueModel);
		return propertyField;
	}
}
