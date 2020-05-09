package nth.reflect.ui.vaadin.button;

import nth.reflect.fw.layer1userinterface.item.Item;

public class Button extends com.vaadin.flow.component.button.Button {

	private static final long serialVersionUID = -4572174577164941204L;

	public Button(ButtonTheme buttonTheme) {
		super();
		addThemeName(buttonTheme.name().toLowerCase());
	}

	public Button(ButtonTheme buttonTheme, Item item) {
		this(buttonTheme);
		setText(item.getText());
		// TODO icon
		getElement().addEventListener("click", e -> item.getAction().run());
	}

}
