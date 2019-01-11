package nth.reflect.fw.javafx.control.tab.form.proppanel;

import javafx.scene.control.Label;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyValidationLabelStyle;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;

public class PropertyValidationLabel extends Label
		implements nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyValidationLabel {

	private static final String NEW_LINE = "\n";

	public PropertyValidationLabel() {
		getStyleClass().add(StyleSheet.createStyleClassName(PropertyValidationLabel.class));
		setWrapText(true);
	}

	private void show() {
		setManaged(true);
	}

	private void hide() {
		setManaged(false);
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(PropertyValidationLabel.class)).getProperties()
				.setTextFill(Color.RED).setFont(PropertyValidationLabelStyle.getFont()).setWrapText(true)
				.setPadding(0, PropertyPanelStyle.PADDING_LEFT_RIGHT, 0, PropertyPanelStyle.PADDING_LEFT_RIGHT);
	}

	@Override
	public void clearMessage() {
		setText("");
		hide();
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
			show();
		} else {
			hide();
		}
	}

}
