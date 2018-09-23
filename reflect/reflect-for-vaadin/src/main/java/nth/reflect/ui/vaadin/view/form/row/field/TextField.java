package nth.reflect.ui.vaadin.view.form.row.field;

import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;
import nth.reflect.ui.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class TextField extends com.vaadin.flow.component.textfield.TextField implements PropertyField {

	public TextField() {
		new StyleBuilder().setPadding(0) .setFor(this);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		if (propertyValue==null) {
			propertyValue="";
		}
		super.setValue((String) propertyValue);
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	
	
}
