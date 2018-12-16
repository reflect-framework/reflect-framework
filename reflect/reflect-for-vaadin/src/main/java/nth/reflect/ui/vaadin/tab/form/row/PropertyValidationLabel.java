package nth.reflect.ui.vaadin.tab.form.row;

import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.ui.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class PropertyValidationLabel extends Div
		implements nth.reflect.fw.ui.tab.form.propertypanel.PropertyValidationLabel {

	private static final String LINE_BREAK = "<BR>";

	public PropertyValidationLabel() {
		new StyleBuilder().setFont("font-size: 8px").setPadding(0, 10, 0, 10)
				.setColor(ReflectColorName.ERROR.BACKGROUND()).setFor(this);
		setText("Required");// TODO works
	}

	@Override
	public void clearMessage() {
		setText("");
		setVisible(false);
	}

	@Override
	public void addMessage(String message) {
		StringBuilder text = new StringBuilder(getText());
		if (text.length() > 0) {
			text.append(LINE_BREAK);
		}
		text.append(message);
		setText(text.toString());
		if (text.toString().trim().length() > 0) {
			setVisible(true);
		} else {
			setVisible(false);
		}
	}

}
