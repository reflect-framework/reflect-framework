package nth.reflect.fw.javafx.control.tab.form.proppanel;

import javafx.scene.control.Label;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.component.PropertyPanelStyle;
import nth.reflect.fw.ui.style.component.PropertyValidationMessagesStyle;

public class PropertyValidationLabel extends Label
		implements nth.reflect.fw.ui.tab.form.propertypanel.PropertyValidationLabel {

	private static final String NEW_LINE = "\n";

	public PropertyValidationLabel() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyValidationLabel.class));
		setWrapText(true);
	}

	private void show() {
		setManaged(true);
	}

	private void hide() {
		setManaged(false);
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyValidationLabel.class)).getProperties()
				.setTextFill(Color.RED).setFont(PropertyValidationMessagesStyle.getFont()).setWrapText(true)
				.setPadding(0, PropertyPanelStyle.getPaddingLeftRight(), 0, PropertyPanelStyle.getPaddingLeftRight());
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
