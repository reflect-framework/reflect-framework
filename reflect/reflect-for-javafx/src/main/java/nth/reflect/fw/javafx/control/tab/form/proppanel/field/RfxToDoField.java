package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import javafx.scene.control.Label;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;

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
