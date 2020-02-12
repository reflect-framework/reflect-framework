package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.factory.DelegatingContractor;
import nth.reflect.fw.layer5provider.Provider;

/**
 * <p>
 * The {@link PropertyFieldProvider} is a {@link Provider} that creates all
 * {@link PropertyField}s. All
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldProvider()} return a
 * {@link PropertyFieldProvider} that contains {@link PropertyFieldFactory}s to
 * create a {@link PropertyField} for the most common data types.
 * </p>
 * <p>
 * You can add custom fields by implementing {@link PropertyField} and
 * {@link PropertyFieldFactory} and overriding the
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldService()}. Look at
 * the existing implementations for inspiration.
 * <p>
 * 
 * @author nilsth
 *
 */
public class PropertyFieldProvider extends DelegatingContractor<PropertyField, PropertyFieldFactoryInfo>
		implements Provider {

	public PropertyFieldProvider(PropertyFieldFactory[] propertyFieldFactories) {
		super(propertyFieldFactories);
	}

}
