package nth.reflect.ui.vaadin.mainwindow.menu;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.charts.model.style.FontWeight;
import com.vaadin.flow.component.tabs.Tab;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class ActionMethodButton extends Tab {

	private Item actionMethodItem;

	public ActionMethodButton(Item actionMethodItem) {
		this.actionMethodItem = actionMethodItem;
		new StyleBuilder().setPadding(0, 0, 0, 30).setFontWeight(FontWeight.NORMAL).setFor(this);

		setVisible(actionMethodItem.isVisible());// TODO dynamic??
		setLabel(actionMethodItem.getText());// TODO dynamic??

		// TODO icon, TODO dynamic??

		addListener(ClickEvent.class, l -> {
			onClick();
		});
	}

	private void onClick() {
		actionMethodItem.getAction().run();
	}

}
