package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import javafx.scene.control.Label;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;

public abstract class ToDoField extends Label implements PropertyField {

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setText(this.getClass().getCanonicalName()+" still needs to be created!!!");
	}
}
