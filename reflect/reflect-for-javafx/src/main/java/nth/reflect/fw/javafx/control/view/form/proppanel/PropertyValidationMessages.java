package nth.reflect.fw.javafx.control.view.form.proppanel;

import java.util.List;

import javafx.scene.control.Label;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.component.PropertyPanelStyle;
import nth.reflect.fw.ui.style.component.PropertyValidationMessagesStyle;

public class PropertyValidationMessages extends Label implements nth.reflect.fw.ui.view.form.propertypanel.PropertyValidationMessages {

	
	public PropertyValidationMessages() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyValidationMessages.class));
	}
	
	@Override
	public void setMessages(List<String> messages) {
		StringBuilder text=new StringBuilder();
		for (String message : messages) {
			if (text.length()>0) {
				text.append("\n");
			}
			text.append(message);
		}
		setText(text.toString());
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyValidationMessages.class)).getProperties()
		.setTextFill(Color.RED)
		.setFont(PropertyValidationMessagesStyle.getFont()).setWrapText(true).setPadding(0, PropertyPanelStyle.getPaddingLeftRight(), 0, PropertyPanelStyle.getPaddingLeftRight());
	}


}
