package nth.reflect.fw.gui.component.tab.form.propertypanel;

import java.util.Optional;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.generic.translatablestring.ReflectTranslatable;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public abstract class PropertyPanelFactory<PROPERTY_PANEL> {

	@ReflectTranslatable
	private static final String COULD_NOT_FIND_A_S_FOR_DOMAIN_OBJECT_PROPERTY_S = "%s could not find a %s for domain object property: %s of type %s";
	private final PropertyFieldProvider propertyFieldService;

	public PropertyPanelFactory(GraphicalUserInterfaceApplication application) {
		propertyFieldService = application.getPropertyFieldService();
	}

	/**
	 * Abstract method to create a property panel. This method uses the
	 * {@link PropertyFieldProvider} to create fields.
	 */
	public PROPERTY_PANEL createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyFieldFactoryInfo info = new PropertyFieldFactoryInfo(formTab, propertyValueModel);

		Optional<PropertyField> propertyField = propertyFieldService.create(info);

		if (propertyField.isPresent()) {
			return createPropertyPanel(formTab, propertyValueModel, propertyField.get());
		}

		UserInterfaceContainer userInterfaceContainer = info.getUserInterfaceContainer();
		throw new ReflectTranslatableException(userInterfaceContainer, COULD_NOT_FIND_A_S_FOR_DOMAIN_OBJECT_PROPERTY_S,
				this.getClass().getCanonicalName(), PropertyFieldFactory.class.getName(),
				info.getPropertyInfo().getCanonicalName(),
				info.getPropertyInfo().getTypeInfo().getType().getCanonicalName());
	}

	/**
	 * Hook method
	 */
	protected abstract PROPERTY_PANEL createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel,
			PropertyField propertyField);
}
