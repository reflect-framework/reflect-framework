package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import javafx.scene.control.Label;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public abstract class RfxToDoField extends Label implements PropertyField {

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setText(this.getClass().getCanonicalName()+" still needs to be created!!!");
	}
}
