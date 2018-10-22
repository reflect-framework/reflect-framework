package nth.reflect.fw.ui.swing.tab.form.proppanel;

import java.awt.Color;
import java.util.List;

import nth.reflect.fw.ui.swing.properygrid.WrapingLabel;

@SuppressWarnings("serial")
public class PropertyValidationMessages extends WrapingLabel
		implements nth.reflect.fw.ui.tab.form.propertypanel.PropertyValidationMessages {

	public PropertyValidationMessages() {
		setForeground(Color.RED);
	}
	
	@Override
	public void setMessages(List<String> messages) {
		StringBuilder text=new StringBuilder();
		for (String message : messages) {
			if (text.length()>0) {
				text.append(WrapingLabel.LINE_BREAK);
			}
			text.append(message);
		}
		setText(text.toString());
		
	}

}
