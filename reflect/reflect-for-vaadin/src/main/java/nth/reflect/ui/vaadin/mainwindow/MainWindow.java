package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;
import nth.reflect.ui.vaadin.UserInterfaceControllerForVaadin;
import nth.reflect.ui.vaadin.tab.Tab;
import nth.reflect.ui.vaadin.tab.TabContentPanel;
import nth.reflect.ui.vaadin.tab.TabHeaderBar;

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
public class MainWindow extends Div implements TabsListener<Tab> {

	private static final long serialVersionUID = -1026778643991244247L;
	static final int Z_INDEX_HEADER = 9998;
	static final int Z_INDEX_MAIN_MENU = 9999;
	static final int Z_INDEX_CONTENT_OVERLAY = Z_INDEX_MAIN_MENU;
	private final TabHeaderBar tabHeaderBar;
	private final Div tabContentPanel;
	private final Tabs<Tab> tabs;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) {

		UserInterfaceControllerForVaadin userInterfaceController = userInterfaceContainer
				.get(UserInterfaceControllerForVaadin.class);
		tabs = userInterfaceController.getTabs();

		MainMenu mainMenu = new MainMenu(userInterfaceContainer);
		tabContentPanel = new TabContentPanel(tabs);
		Overlay overlay = new Overlay();
		tabHeaderBar = new TabHeaderBar(tabs);
		HeaderBar headerBar = new HeaderBar(userInterfaceContainer, this);

		// TODO add both tabHeaderBar and headerBar to MainWindow
		add(headerBar, mainMenu, tabContentPanel, overlay);

		// add the MainMenu last as TabsListener: The resizeChildrenOnBrowser
		// method needs to called AFTER other childeren are updated
		tabs.addListener(this);

		createTabs();

		UI.getCurrent().getPage().executeJavaScript(JavaScriptToSetCssColors.createFor(userInterfaceContainer));
	}


	private void createTabs() {
		LoremIpsumTab tab1 = new LoremIpsumTab("Tab one");
		tabs.add(tab1);

		LoremIpsumTab tab2 = new LoremIpsumTab("Tab two");
		tabs.add(tab2);

		LoremIpsumTab tab3 = new LoremIpsumTab("Tab three");
		tabs.add(tab3);
	}

	private void resizeChildernOnBrowser() {
		UI.getCurrent().getPage().executeJavaScript("updateGui()");
	}

	public Div getTabContentPanel() {
		return tabContentPanel;
	}

	public TabHeaderBar getTabHeaderBar() {
		return tabHeaderBar;
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		// Do nothing
	}

	@Override
	public void onAddTab(Tab newTab) {
		// Do nothing }
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		resizeChildernOnBrowser();
	}

}
