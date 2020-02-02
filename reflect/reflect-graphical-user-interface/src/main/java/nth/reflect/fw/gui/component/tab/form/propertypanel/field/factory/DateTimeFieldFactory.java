package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class DateTimeFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		PropertyInfo propertyInfo = info.getPropertyInfo();
		Optional<DateTimeFieldModeType> mode = DateTimeFieldModeType.getFor(propertyInfo);
		return mode.isPresent() ;
	}

	protected Optional<DateTimeFieldModeType> getDateTimeFieldModeType(PropertyInfo propertyInfo) {
		return DateTimeFieldModeType.getFor(propertyInfo);
	}
}
