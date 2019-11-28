package nth.reflect.ui.vaadin.mainwindow.tabsheaderbar;

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tabs;

import nth.reflect.fw.gui.component.tab.TabsListener;
import nth.reflect.ui.vaadin.tab.Tab;

public class TabsHeaderBar extends Tabs implements TabsListener<Tab> {
	private static final long serialVersionUID = 5521742911393896062L;
	private final nth.reflect.fw.gui.component.tab.Tabs<Tab> tabs;

	public TabsHeaderBar(nth.reflect.fw.gui.component.tab.Tabs<Tab> tabs) {
		this.tabs = tabs;
		setOrientation(Tabs.Orientation.HORIZONTAL);
		tabs.addListener(this);
	}

	private Optional<Component> findTab(Tab tabToFind) {
		return getChildren().filter(child -> child instanceof TabHeader && ((TabHeader) child).getTab() == tabToFind)
				.findFirst();
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		Optional<Component> foundTab = findTab(removedTab);
		if (foundTab.isPresent()) {
			remove(foundTab.get());
		}
	}

	@Override
	public void onAddTab(Tab newTab) {
		TabHeader tabHeader = new TabHeader(tabs, newTab);
		add(tabHeader);
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		Optional<Component> foundTab = findTab(selectedTab);
		if (foundTab.isPresent()) {
			setSelectedTab((com.vaadin.flow.component.tabs.Tab) foundTab.get());
		}
	}
}
