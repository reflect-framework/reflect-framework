package nth.reflect.ui.vaadin.tab.form.row;

import java.awt.Color;
import java.util.List;

import com.vaadin.flow.component.html.Div;

import nth.reflect.ui.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class PropertyValidationMessages extends Div implements  nth.reflect.fw.ui.tab.form.propertypanel.PropertyValidationMessages {


	private static final String LINE_BREAK = "<BR>";

	public PropertyValidationMessages() {
		new StyleBuilder().setFont("font-size: 8px").setPadding(0,10,0,10).setColor(Color.RED).setFor(this);
		setText("Required");//TODO works
	}

	@Override
	public void setMessages(List<String> messages) {
		StringBuilder text=new StringBuilder();
		for (String message : messages) {
			if (text.length()>0) {
				text.append(LINE_BREAK);
			}
			text.append(message);
		}
		setText(text.toString());
	}

}
