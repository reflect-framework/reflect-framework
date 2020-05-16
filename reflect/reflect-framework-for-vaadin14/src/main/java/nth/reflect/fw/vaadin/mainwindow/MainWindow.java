package nth.reflect.fw.vaadin.mainwindow;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.vaadin.UserInterfaceControllerForVaadin14;
import nth.reflect.fw.vaadin.mainwindow.content.ContentPanel;
import nth.reflect.fw.vaadin.mainwindow.menu.MainMenu;
import nth.reflect.fw.vaadin.mainwindow.tabsheaderbar.TabsHeaderBar;

public class MainWindow extends AppLayout implements nth.reflect.fw.gui.component.mainwindow.MainWindow {

	private static final long serialVersionUID = -3314120849528155458L;
	final static boolean TOUCH_OPTIMIZED = true;
	private ContentPanel content;
	private TabsHeaderBar tabsHeaderBar;
	private nth.reflect.fw.gui.component.tab.Tabs<nth.reflect.fw.vaadin.tab.Tab> tabs;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) {
		setDrawerOpened(true);

		DrawerToggle menuToggleButton = createMenuToggleButton();
		ApplicationTitle title = new ApplicationTitle(userInterfaceContainer);
//		Image logo = createLogo();
		addToNavbar(TOUCH_OPTIMIZED, menuToggleButton, title);

//		ApplicationTitle title2 = new ApplicationTitle(userInterfaceContainer);
//		Hr verticalDivider = new Hr();
		MainMenu mainMenu = new MainMenu(userInterfaceContainer);
//		addToDrawer(title2, verticalDivider, mainMenu);
		addToDrawer(mainMenu);

		tabs = getTabs(userInterfaceContainer);

		tabsHeaderBar = new TabsHeaderBar(tabs);
		addToNavbar(tabsHeaderBar);

		content = new ContentPanel(tabs);
		setContent(content);
	}

	private nth.reflect.fw.gui.component.tab.Tabs<nth.reflect.fw.vaadin.tab.Tab> getTabs(
			UserInterfaceContainer userInterfaceContainer) {
		UserInterfaceControllerForVaadin14 userInterfaceController = userInterfaceContainer
				.get(UserInterfaceControllerForVaadin14.class);
		nth.reflect.fw.gui.component.tab.Tabs<nth.reflect.fw.vaadin.tab.Tab> tabs = userInterfaceController.getTabs();
		return tabs;
	}

	private DrawerToggle createMenuToggleButton() {
		return new DrawerToggle();
	}

}
