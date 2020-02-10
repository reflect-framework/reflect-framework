package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.factory.DelegatingContractor;
import nth.reflect.fw.layer5provider.Provider;

/**
 * <p>
 * The {@link PropertyFieldService} is a {@link Provider} that creates all
 * {@link PropertyField}s. All
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldService()} return a
 * {@link PropertyFieldService} that contains {@link PropertyFieldFactory}s to
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
public class PropertyFieldService extends DelegatingContractor<PropertyField, PropertyFieldFactoryInfo>
		implements Provider {

	public PropertyFieldService(PropertyFieldFactory[] propertyFieldFactories) {
		super(propertyFieldFactories);
	}

}
