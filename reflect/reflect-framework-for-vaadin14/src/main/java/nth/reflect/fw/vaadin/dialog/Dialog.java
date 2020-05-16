package nth.reflect.fw.vaadin.dialog;

import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.charts.model.style.FontWeight;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.fw.vaadin.button.Button;
import nth.reflect.fw.vaadin.button.ButtonTheme;
import nth.reflect.fw.vaadin.css.Overflow;
import nth.reflect.fw.vaadin.css.StyleBuilder;

public class Dialog extends com.vaadin.flow.component.dialog.Dialog {

	private static final long serialVersionUID = -2961655999220965319L;
	private final Div titleDiv;
	private final Div messageDiv;
	private final HorizontalLayout buttonBar;

	public Dialog() {
		titleDiv = createTitleDiv();
		messageDiv = createMessageDiv();
		buttonBar = createButtonBar();
		add(titleDiv, messageDiv, buttonBar);
	}

	public void open(String title, String message, List<Item> items) {
		setTitle(title);
		setMessage(message);
		setButtons(items);
		open();
	}

	private void setMessage(String message) {
		message = message.replace("\r\n", "<br>");
		message = message.replace("\r", "<br>");
		message = message.replace("\n", "<br>");
//		messageDiv.setText(message);
		messageDiv.getElement().setProperty("innerHTML", message);
	}

	private void setTitle(String title) {
		titleDiv.setText(title);
	}

	private void setButtons(List<Item> items) {
		buttonBar.removeAll();
		Button[] buttons = createButtons(items);
		buttonBar.add(buttons);
	}

	private HorizontalLayout createButtonBar() {
		HorizontalLayout buttonBar = new HorizontalLayout();
		return buttonBar;
	}

	private Button[] createButtons(List<Item> items) {
		Button[] buttons = new Button[items.size()];
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			Button button = createButton(item);
			// TODO align buttons to the right (float:right does not work???)
			if (isLastButton(items, i)) {
				button.setAutofocus(true);
				button.addClickShortcut(Key.ENTER);
				button.addClickShortcut(Key.SPACE);
			}
			buttons[i] = button;
		}
		return buttons;
	}

	private Button createButton(Item item) {
		if (item instanceof DialogCloseItem) {
			item.setAction(createCloseAction());
		}
		Button button = new Button(ButtonTheme.SECONDARY, item);
		return button;
	}

	private Action createCloseAction() {
		return new Action() {
			@Override
			public void run() {
				close();
			}
		};
	}

	private boolean isLastButton(List<Item> items, int i) {
		return i == items.size() - 1;
	}

	private Div createMessageDiv() {
		Div messageLabel = new Div();
		new StyleBuilder()
				.setMargin(10, 0, 10, 0)
				.setProperty("max-height", "300px")
				.setOverflow(Overflow.AUTO)
				.setFor(messageLabel);
		return messageLabel;
	}

	private Div createTitleDiv() {
		Div titleDiv = new Div();
		new StyleBuilder().setFontWeight(FontWeight.BOLD).setFor(titleDiv);
		return titleDiv;
	}
}
