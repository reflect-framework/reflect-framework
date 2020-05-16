package nth.reflect.fw.vaadin.mainwindow.content;

import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.TabsListener;
import nth.reflect.fw.vaadin.tab.Tab;

public class ContentPanel extends Div implements TabsListener<Tab> {

	private static final long serialVersionUID = 7745136528688167236L;
	private Tabs<Tab> tabs;

	public ContentPanel(Tabs<Tab> tabs) {
		this.tabs = tabs;
		setSizeFull();
		tabs.addListener(this);
	}

	public void showSelectedTabOnly() {
		Tab selectedTab = tabs.getSelected();
		for (Component child : getChildren().collect(Collectors.toList())) {
			child.setVisible(child == selectedTab);
		}
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		remove(removedTab);
		showSelectedTabOnly();
	}

	@Override
	public void onAddTab(Tab newTab) {
		add(newTab);
		showSelectedTabOnly();
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		showSelectedTabOnly();
	}
}
