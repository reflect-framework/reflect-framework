package nth.reflect.ui.vaadin.mainwindow;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.ui.vaadin.view.container.TabView;
import nth.reflect.ui.vaadin.view.container.TabViewController;

/**
 * The {@link MainWindow} represents the Graphical UserTestObject Interface with
 * <a href="http://vaadin.com">Vaadin</a> {@link Component}s
 * 
 * @author nilsth
 *
 */

@HtmlImport("bower_components/font-roboto/roboto.html")
@JavaScript("bower_components/jquery/3.3.1-1/jquery.js")
@JavaScript("js/main-window.js")
public class MainWindow extends Div {

	private static final long serialVersionUID = -1026778643991244247L;
	static final int Z_INDEX_HEADER = 9998;
	static final int Z_INDEX_MAIN_MENU = 9999;
	static final int Z_INDEX_CONTENT_OVERLAY = Z_INDEX_MAIN_MENU;
	static final Color BLACK_WITH_OPACITY = new Color(0f, 0f, 0f, 0.5f);
	private final TabViewController tabViewController;
	private final TabHeaderBar tabHeaderBar;
	private final Div tabViewContainer;
	private Map<Tab, TabView> tabsAndViews;
	private Set<TabView> selectedTabView;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) {
		selectedTabView=new HashSet<>();
		MainMenu mainMenu = new MainMenu(userInterfaceContainer);
		tabViewContainer = new TabViewContainer();
		Overlay overlay = new Overlay();
		tabHeaderBar = new TabHeaderBar(this);
		HeaderBar header = new HeaderBar(userInterfaceContainer, this);

		createTabs();

		add(header, mainMenu, tabViewContainer, overlay);

		tabViewController = new TabViewController(this);
	}

	

	public TabViewController getTabViewController() {
		return tabViewController;
	}

	public void onRemoveTab(TabView view) {
		// TODO Auto-generated method stub

	}

	public void onAddTab(TabView newView) {
		Tab tab = new Tab(newView.getViewTitle());
		tabsAndViews.put(tab, newView);
		tabHeaderBar.add(tab);
		tabViewContainer.add(newView);
		onSelectTabView(newView);
	}

	/**
	 * TODO: this method can be called by
	 * {@link TabViewController#setSelectedView(TabView)}, e.g. when an existing
	 * tabView should regain focus. In this case we should also select the tab
	 * with
	 * {@link #onSelectTab(com.vaadin.flow.component.tabs.Tabs.SelectedChangeEvent)},
	 * but this would case a endless loop!!!!
	 * 
	 * @param selectedView
	 */
	public void onSelectTabView(TabView selectedView) {
		selectedTabView.forEach(tabView -> tabView.setVisible(false));
		selectedTabView.clear();
		selectedView.setVisible(true);
		selectedTabView.add(selectedView);
		for (Tab tab : tabsAndViews.keySet()) {
			if (tabsAndViews.get(tab) == selectedView) {
				tabHeaderBar.setSelectedTab(tab);
				break;
			}
		}
		selectedView.onViewActivate();
		resizeChildernOnBrowser();
	}

	private void createTabs() {
		Tab tab1 = new Tab("Tab one");
		LoremIpsumTabView view1 = new LoremIpsumTabView(tab1);

		Tab tab2 = new Tab("Tab two");
		LoremIpsumTabView view2 = new LoremIpsumTabView(tab2);
		view2.setVisible(false);

		Tab tab3 = new Tab("Tab three");
		LoremIpsumTabView view3 = new LoremIpsumTabView(tab3);
		view3.setVisible(false);

		tabsAndViews = new HashMap<>();
		tabsAndViews.put(tab1, view1);
		tabsAndViews.put(tab2, view2);
		tabsAndViews.put(tab3, view3);

		tabHeaderBar.add(tab1, tab2, tab3);

		tabViewContainer.add(view1, view2, view3);

		selectedTabView.add(view1);
	}

	private void resizeChildernOnBrowser() {
		UI.getCurrent().getPage().executeJavaScript("updateGui()");
	}

	public Map<Tab, TabView> getTabsAndViews() {
		return tabsAndViews;
	}

	public Div getTabViewContainer() {
		return tabViewContainer;
	}

	public Set<TabView> getSelectedTabView() {
		return selectedTabView;
	}

	public TabHeaderBar getTabHeaderBar() {
		return tabHeaderBar;
	}

}
