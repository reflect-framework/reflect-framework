package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsMethod;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * {@insert ComboBoxField}
 * <p>
 * Supports:
 * <ul>
 * <li>{@link java.lang.Enum}</li>
 * <li>{@link DomainObjectProperty}s that has a {@link OptionsMethod}</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public abstract class ComboBoxFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		boolean isEnum = propertyInfo.getTypeInfo().isEnum();
		boolean hasOptions = propertyInfo.hasOptions();
		return isEnum || hasOptions;
	}

}
