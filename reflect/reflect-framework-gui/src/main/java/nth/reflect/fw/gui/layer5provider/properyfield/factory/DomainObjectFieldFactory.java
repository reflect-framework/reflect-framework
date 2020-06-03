package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * {@insert DomainObjectField}
 * <p>
 * Supports:
 * <ul>
 * <li>{@link DomainObject}s</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public abstract class DomainObjectFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		boolean isDomainClass = propertyInfo.getTypeInfo().isDomainClass();
		return isDomainClass;
	}

}
