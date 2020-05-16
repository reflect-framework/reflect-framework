package nth.reflect.fw.vaadin.mainwindow.tabsheaderbar;

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tabs;

import nth.reflect.fw.gui.component.tab.TabsListener;
import nth.reflect.fw.vaadin.tab.Tab;

public class TabsHeaderBar extends Tabs implements TabsListener<Tab> {
	private static final long serialVersionUID = 5521742911393896062L;
	private final nth.reflect.fw.gui.component.tab.Tabs<Tab> tabs;

	public TabsHeaderBar(nth.reflect.fw.gui.component.tab.Tabs<Tab> tabs) {
		this.tabs = tabs;
		setOrientation(Tabs.Orientation.HORIZONTAL);
		addSelectedChangeListener(this::onTabHeaderClick);
		tabs.addListener(this);
	}

	private void onTabHeaderClick(SelectedChangeEvent event) {
		com.vaadin.flow.component.tabs.Tab selectedTabHeader = getSelectedTab();
		Optional<Component> foundTabHeader = findTab(selectedTabHeader);
		if (foundTabHeader.isPresent()) {
			TabHeader tabHeader = (TabHeader) foundTabHeader.get();
			tabs.setSelected(tabHeader.getTab());
		}
	}

	
	private Optional<Component> findTab(com.vaadin.flow.component.tabs.Tab tabToFind) {
		return getChildren().filter(child -> child instanceof TabHeader && ((TabHeader) child) == tabToFind)
				.findFirst();
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
