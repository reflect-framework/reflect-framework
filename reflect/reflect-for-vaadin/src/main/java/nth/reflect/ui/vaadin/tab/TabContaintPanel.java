package nth.reflect.ui.vaadin.tab;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;

public class TabContaintPanel extends Div implements TabsListener<Tab> {

	private static final long serialVersionUID = -9128233971995088944L;

	/**
	 * The {@link TabContaintPanel} HTML element is a placeholder (a container)
	 * for the tab contents. The {@link TabContaintPanel} element and all its
	 * children will be repositioned and resized with javascript (see
	 * main-window.js)
	 * 
	 * @param tabs
	 */

	public TabContaintPanel(Tabs<Tab> tabs) {
		tabs.addListener(this);
		setId("tabContentPanel");
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		remove(removedTab);
	}

	@Override
	public void onAddTab(Tab newTab) {
		add(newTab);
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		List<Component> children = getChildren().collect(Collectors.toList());
		for (Component component : children) {
			if (component instanceof Tab) {
				Tab tab = (Tab) component;
				tab.setVisible(tab == selectedTab);
			}
		}
	}
}
