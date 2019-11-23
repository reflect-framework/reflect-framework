package nth.reflect.ui.vaadin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@PWA(name = "My Application", shortName = "My App")
public class ReflectForVaadin14Application extends AppLayout {

	final static boolean TOUCH_OPTIMIZED = true;
	private ContentPanel content;
	private Map<Tab, Component> tabsToPages;
	private Tabs tabsHeader;

	public ReflectForVaadin14Application() {
		DrawerToggle menuToggleButton = createMenuToggleButton();
		Image logo = createLogo();
		addToNavbar(TOUCH_OPTIMIZED, menuToggleButton, logo);

		Tabs menuItems = createMenuItems();
		addToDrawer(menuItems);

		tabsToPages = createTabsToPages();

		tabsHeader = createTabsHeader();
		addToNavbar(tabsHeader);

		content = createContentPanel();
		setContent(content);
	}

	private ContentPanel createContentPanel() {
		ContentPanel contentPanel = new ContentPanel();
		Component selectedPage = getSelectedPage(tabsHeader);
		for (Component page : tabsToPages.values()) {
			page.setVisible(page == selectedPage);
			contentPanel.add(page);
		}
		return contentPanel;
	}

	private Tabs createTabsHeader() {
		Tabs tabsHeader = new Tabs();
		tabsHeader.setOrientation(Tabs.Orientation.HORIZONTAL);
		Set<Tab> tabs = tabsToPages.keySet();
		for (Tab tab : tabs) {
			tabsHeader.add(tab);
		}
		tabsHeader.addSelectedChangeListener(l -> {
			Component selectedPage = getSelectedPage(tabsHeader);
			content.show(selectedPage);
		});
		return tabsHeader;
	}

	private Component getSelectedPage(Tabs tabsHeader) {
		Tab selectedTab = tabsHeader.getSelectedTab();
		Component selectedPage = tabsToPages.get(selectedTab);
		return selectedPage;
	}

	private Map<Tab, Component> createTabsToPages() {
		tabsToPages = new HashMap();
		for (int i = 1; i < 20; i++) {
			Tab tab = createTab(i);
			Div page = createPage(i);
			tabsToPages.put(tab, page);
		}
		return tabsToPages;
	}

	private Image createLogo() {
		Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
		img.setHeight("44px");
		return img;
	}

	private DrawerToggle createMenuToggleButton() {
		return new DrawerToggle();
	}

	private Tabs createMenuItems() {
		Tabs menuItems = new Tabs(new Tab("Home"), new Tab("About"));
		menuItems.setOrientation(Tabs.Orientation.VERTICAL);
		return menuItems;
	}

	private Div createPage(int i) {
		Div page = new Div();
		page.add(new Text("Page content for tab " + i));
		return page;
	}

	private Tab createTab(int i) {
		Tab tab = new Tab("Tab " + i);
		Icon closeIcon = VaadinIcon.CLOSE_SMALL.create();
		closeIcon.addClickListener(l -> {
			tabsHeader.remove(tab);
			content.remove(tabsToPages.get(tab));
		});
		tab.addComponentAtIndex(1, closeIcon);
		return tab;
	}
}
