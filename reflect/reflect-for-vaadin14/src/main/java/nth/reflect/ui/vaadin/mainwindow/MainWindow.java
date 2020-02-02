package nth.reflect.ui.vaadin.mainwindow;

import java.lang.reflect.Field;

import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
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

	private PropertyDescriptor<Boolean, Boolean> getOverlayProperty() {
		Field overlayPropertyField;
		try {
			overlayPropertyField = AppLayout.class.getDeclaredField("overlayProperty");
			overlayPropertyField.setAccessible(true);
			PropertyDescriptor<Boolean, Boolean> overlayProperty2 = (PropertyDescriptor<Boolean, Boolean>) overlayPropertyField
					.get(this);
			return overlayProperty2;
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

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
