package nth.reflect.ui.vaadin.mainwindow;

import java.util.Map;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import nth.reflect.ui.vaadin.view.container.TabView;

public final class TabHeaderBar extends Tabs {

	private static final long serialVersionUID = 4516096817276708483L;
	private final MainWindow mainWindow;

	public TabHeaderBar(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setId("tabHeaderBar");
		addSelectedChangeListener(this::onSelectTab);
	}
	
	/**
	 * Called by remote GUI when user clicks on a tab
	 * @param event of user clicking on tab
	 */
	public void onSelectTab(Tabs.SelectedChangeEvent event) {
		Tab selectedTab = getSelectedTab();
		Map<Tab, TabView> tabsAndViews = mainWindow.getTabsAndViews();
		TabView selectedView = tabsAndViews.get(selectedTab);
		mainWindow.onSelectTabView(selectedView);
	}
}
