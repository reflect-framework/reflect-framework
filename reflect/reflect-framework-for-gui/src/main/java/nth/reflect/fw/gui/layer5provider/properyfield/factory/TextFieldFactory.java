package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

/**
 * {@insert TextField}
 * <p>
 * Supports:
 * <ul>
 * <li>All types that are supported by the {@link StringConverterProvider}</li>
 * </ul>
 */
public abstract class TextFieldFactory implements PropertyFieldFactory {

	// TODO for either StringConverter or BiDirectionalStringConverter see #482
	// StringConverters without fromString must be readonly fields see #483
	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Optional<StringConverter> optionalStringConverter = propertyInfo.getStringConverter();
		return optionalStringConverter.isPresent();
	}

	public static TextFieldModeType getTextFieldModeType(PropertyInfo propertyInfo) {
		return TextFieldModeType.getFor(propertyInfo);
	}

}
