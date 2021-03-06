package nth.reflect.fw.swing.tab.form.proppanel;

import java.awt.Color;

import nth.reflect.fw.swing.properygrid.WrapingLabel;

@SuppressWarnings("serial")
public class PropertyValidationLabel extends WrapingLabel
		implements nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyValidationLabel {

	public PropertyValidationLabel() {
		setForeground(Color.RED);
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
			text.append(NEW_LINE);
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
