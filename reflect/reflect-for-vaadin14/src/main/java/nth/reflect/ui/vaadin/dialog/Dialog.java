package nth.reflect.ui.vaadin.dialog;

import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.charts.model.style.FontWeight;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.ui.vaadin.button.Button;
import nth.reflect.ui.vaadin.button.ButtonTheme;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class Dialog extends com.vaadin.flow.component.dialog.Dialog {

	private static final long serialVersionUID = -2961655999220965319L;
	private final Div titleDiv;
	private final Div messageDiv;
	private final HorizontalLayout buttonBar;

	public Dialog() {
		titleDiv = createTitleDiv();
		messageDiv = createMessageDiv();
		buttonBar = createButtonBar();
		new StyleBuilder().setOverflow(Overflow.HIDDEN).setFor(this);
		add(titleDiv, messageDiv, buttonBar);
	}

	public void open(String title, String message, List<Item> items) {
		setTitle(title);
		setMessage(message);
		setButtons(items);
		open();
	}

	private void setMessage(String message) {
		messageDiv.setText(message);
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
			if (isLastButton(items, i)) {
//				button.setThemeName("primary");
				button.addClickShortcut(Key.ENTER);// TODO not working???
				button.addClickShortcut(Key.SPACE);// TODO not working???
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
		return i == items.size();
	}


	private Div createMessageDiv() {
		Div messageLabel = new Div();
		new StyleBuilder().setPadding(5, 0, 5, 0).setOverflow(Overflow.HIDDEN) .setFor(messageLabel);
		return messageLabel;
	}

	private Div createTitleDiv() {
		Div titleDiv = new Div();
		new StyleBuilder().setFontWeight(FontWeight.BOLD).setFor(titleDiv);
		return titleDiv;
	}
}
