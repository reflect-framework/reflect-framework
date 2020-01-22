package nth.reflect.ui.vaadin.mainwindow.menu;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.charts.model.style.FontWeight;
import com.vaadin.flow.component.tabs.Tab;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class ActionMethodButton extends Tab {

	private static final long serialVersionUID = -7277277127417590632L;
	private Item actionMethodItem;

	public ActionMethodButton(Item actionMethodItem) {
		this.actionMethodItem = actionMethodItem;
		new StyleBuilder().setPadding(0, 0, 0, 30).setFontWeight(FontWeight.NORMAL).setFor(this);

		setVisible(actionMethodItem.isVisible());// TODO dynamic??
		setLabel(actionMethodItem.getText());// TODO dynamic??

		// TODO icon, TODO dynamic??

		addListener(TabClickEvent.class, createListener());//Lambda does not work for maven-compiler-plugin
	}

	@SuppressWarnings({ "serial" })
	private ComponentEventListener<TabClickEvent> createListener() {
		return new ComponentEventListener<TabClickEvent>() {
			@Override
			public void onComponentEvent(TabClickEvent event) {
				onClick();			}
		};
	}

	private void onClick() {
		actionMethodItem.getAction().run();
	}

}
