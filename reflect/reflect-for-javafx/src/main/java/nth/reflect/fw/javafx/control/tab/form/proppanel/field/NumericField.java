package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.text.Format;
import java.text.ParseException;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

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
		Format format = getPropertyValueModel().getPropertyInfo().getFormat();
		Object value;
		try {
			value = format.parseObject(newValue);
			getPropertyValueModel().setValue(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		PropertyValueModel propertyValueModel = getPropertyValueModel();
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		String formatedValue = propertyInfo.getFormatedValue(propertyValue);
		setText(formatedValue);
	}

}
