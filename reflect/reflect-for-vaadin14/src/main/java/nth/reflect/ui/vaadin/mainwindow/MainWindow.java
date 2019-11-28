package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.ui.vaadin.UserInterfaceControllerForVaadin14;
import nth.reflect.ui.vaadin.mainwindow.content.ContentPanel;
import nth.reflect.ui.vaadin.mainwindow.menu.MainMenu;
import nth.reflect.ui.vaadin.mainwindow.tabsheaderbar.TabsHeaderBar;

public class MainWindow extends AppLayout implements nth.reflect.fw.gui.component.mainwindow.MainWindow {

	private static final long serialVersionUID = -3314120849528155458L;
	final static boolean TOUCH_OPTIMIZED = true;
	private ContentPanel content;
	private TabsHeaderBar tabsHeaderBar;
	private nth.reflect.fw.gui.component.tab.Tabs<nth.reflect.ui.vaadin.tab.Tab> tabs;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) {
		DrawerToggle menuToggleButton = createMenuToggleButton();

		Div title = createTitle(userInterfaceContainer);
//		Image logo = createLogo();
		addToNavbar(TOUCH_OPTIMIZED, menuToggleButton, title);

		MainMenu mainMenu = new MainMenu(userInterfaceContainer);
		addToDrawer(mainMenu);

		tabs = getTabs(userInterfaceContainer);

		tabsHeaderBar = new TabsHeaderBar(tabs);
		addToNavbar(tabsHeaderBar);

		content = new ContentPanel(tabs);
		setContent(content);
	}

	private Div createTitle(UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationClassInfo applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		String title = applicationClassInfo.getDisplayName();
		Div div = new Div();
		div.setText(title);
		return div;
	}

	private nth.reflect.fw.gui.component.tab.Tabs<nth.reflect.ui.vaadin.tab.Tab> getTabs(
			UserInterfaceContainer userInterfaceContainer) {
		UserInterfaceControllerForVaadin14 userInterfaceController = userInterfaceContainer
				.get(UserInterfaceControllerForVaadin14.class);
		nth.reflect.fw.gui.component.tab.Tabs<nth.reflect.ui.vaadin.tab.Tab> tabs = userInterfaceController.getTabs();
		return tabs;
	}

	private Image createLogo() {
		Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
		img.setHeight("44px");
		return img;
	}

	private DrawerToggle createMenuToggleButton() {
		return new DrawerToggle();
	}

}
