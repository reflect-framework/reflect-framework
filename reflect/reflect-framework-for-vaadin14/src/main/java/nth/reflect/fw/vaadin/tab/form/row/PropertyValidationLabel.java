package nth.reflect.fw.vaadin.tab.form.row;

import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyValidationLabelStyle;
import nth.reflect.fw.vaadin.css.SizeUnit;
import nth.reflect.fw.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class PropertyValidationLabel extends Div
		implements nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyValidationLabel {

	private static final String LINE_BREAK = "<BR>";

	public PropertyValidationLabel() {
		new StyleBuilder()
				.setFontSize(PropertyValidationLabelStyle.FONT_SIZE, SizeUnit.PX)
				.setFont(PropertyValidationLabelStyle.FONT)
				.setPadding(0, PropertyValidationLabelStyle.PADDING_RIGHT, 0, PropertyValidationLabelStyle.PADDING_LEFT)
				.setColor(PropertyValidationLabelStyle.FONT_COLOR)
				.setFor(this);
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
