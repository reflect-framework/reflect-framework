package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Optional;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class NumericField extends TextField {

	public NumericField(PropertyValueModel propertyValueModel) {
		super(propertyValueModel);
	}

	@Override
	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (!newValue.matches("\\d*")) {
			newValue = newValue.replaceAll("[^\\d]", "");
			// TODO minus, point, length
		}
		setText(newValue);

		Optional<StringConverter> stringConverter = getPropertyValueModel().getPropertyInfo().getStringConverter();
		if (stringConverter.isPresent()) {
			try {
				Object value = stringConverter.get().fromString(newValue);
				getPropertyValueModel().setValue(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

}
