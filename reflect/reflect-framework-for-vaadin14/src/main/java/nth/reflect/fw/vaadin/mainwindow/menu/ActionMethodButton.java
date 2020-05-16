package nth.reflect.fw.vaadin.mainwindow.menu;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.charts.model.style.FontWeight;
import com.vaadin.flow.component.tabs.Tab;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.vaadin.css.StyleBuilder;

public class ActionMethodButton extends Tab {

	private static final long serialVersionUID = -7277277127417590632L;
	private Item actionMethodItem;

	public ActionMethodButton(Item actionMethodItem) {
		this.actionMethodItem = actionMethodItem;
		new StyleBuilder().setPadding(0, 0, 0, 30).setFontWeight(FontWeight.NORMAL).setFor(this);

		setVisible(actionMethodItem.isVisible());// TODO dynamic??
		setLabel(actionMethodItem.getText());// TODO dynamic??

		// TODO icon, TODO dynamic??

		getElement().addEventListener("click", e -> {onClick();});
	}


	private void onClick() {
		actionMethodItem.getAction().run();
	}

}
