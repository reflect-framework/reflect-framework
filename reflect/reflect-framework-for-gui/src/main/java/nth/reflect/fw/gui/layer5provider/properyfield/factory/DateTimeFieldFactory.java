package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * {@insert DateTimeField}
 * <p>
 * {@insert DateTimeFieldModeType}
 * 
 * @author nilsth
 *
 */
public abstract class DateTimeFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Optional<DateTimeFieldModeType> mode = DateTimeFieldModeType.getFor(propertyInfo);
		return mode.isPresent();
	}

	protected Optional<DateTimeFieldModeType> getDateTimeFieldModeType(PropertyInfo propertyInfo) {
		return DateTimeFieldModeType.getFor(propertyInfo);
	}
}
