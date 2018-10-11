package nth.reflect.ui.vaadin.mainwindow;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.selection.SelectionEvent;

import javafx.scene.control.TreeItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.appinfo.ApplicationInfo;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.fw.ui.item.method.menu.MainMenuItems;
import nth.reflect.ui.vaadin.css.Cursor;
import nth.reflect.ui.vaadin.css.Display;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.Position;
import nth.reflect.ui.vaadin.css.SizeUnit;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.view.container.TabView;
import nth.reflect.ui.vaadin.view.container.TabViewController;

/**
 * The {@link MainWindow} represents the Graphical UserTestObject Interface with
 * <a href="http://vaadin.com">Vaadin</a> {@link Component}s
 * 
 * @author nilsth
 *
 */
@HtmlImport("styles/reflect-resize.html")
@HtmlImport("bower_components/font-roboto/roboto.html")
@JavaScript("bower_components/jquery/3.3.1-1/jquery.js")
public class MainWindow extends Div {

	private static final long serialVersionUID = -1026778643991244247L;
	private static final int Z_INDEX_HEADER = 9998;
	private static final int Z_INDEX_MAIN_MENU = 9999;
	private static final int Z_INDEX_CONTENT_OVERLAY = Z_INDEX_MAIN_MENU;
	private static final Color BLACK_WITH_OPACITY = new Color(0f, 0f, 0f, 0.5f);
	private final UserInterfaceContainer userInterfaceContainer;
	private final TabViewController tabViewController;
	private Tabs tabsBar;
	private final Div tabViewContainer;
	private Map<Tab, TabView> tabsToViews;
	private Set<Component> selectedTabView;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer = userInterfaceContainer;
		Div mainMenu = createMainMenu();
		tabViewContainer = createTabViewContainer();
		Div overlay = createOverlay();
		HorizontalLayout header = createHeader();
		add(header, mainMenu, tabViewContainer, overlay);

		tabViewController = new TabViewController(this);
	}

	/**
	 * @return An mainMenu element. The menu element is a placeholder (a
	 *         container) for the main menu. The mainMenu element and all its
	 *         children will be repositioned and resized with javascript (see
	 *         reflect-resize.html)
	 */
	private Div createMainMenu() {
		Div mainMenu = new Div();
		mainMenu.setId("main-menu");
		new StyleBuilder().setProperty("border-right", "1px solid lightgray").setZIndex(Z_INDEX_MAIN_MENU)
				.setBackground(Color.WHITE).setFor(mainMenu);

		Grid<Item> mainMenuContent = createMainMenuContent();
		mainMenu.add(mainMenuContent);

		return mainMenu;
	}

	private Grid<Item> createMainMenuContent() {
		Grid<Item> grid = new Grid<>();
		new StyleBuilder().setOverflow(Overflow.AUTO).setFor(grid);
		grid.setItems(createMainMenuItems());
		grid.addColumn(Item::getText);
		grid.addSelectionListener(this::onMainMenuItemSelected);
		return grid;
	}

	private void onMainMenuItemSelected(SelectionEvent<Grid<Item>, Item> event) {
		Optional<Item> item = event.getFirstSelectedItem();
		if (item.isPresent()) {
			item.get().getAction().run();
		}
	}

	private List<Item> createMainMenuItems() {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);

		Collection<MethodOwnerItem> serviceObjectItems = new MainMenuItems(userInterfaceContainer);

		List<Item> items = new ArrayList<>();
		// TODO wait for Vaadin10 to release a TreeGrid (current state: planned
		// but no time line)
		for (MethodOwnerItem serviceObjectItem : serviceObjectItems) {
			items.add(serviceObjectItem);
			for (Item actionMethodItem : serviceObjectItem.getChildren()) {
				items.add(actionMethodItem);
			}
		}
		return items;
	}

	/**
	 * @return An content element. The content element is a placeholder (a
	 *         container) for the tab contents. The content element and all its
	 *         children will be repositioned and resized with javascript (see
	 *         reflect-resize.html)
	 */
	private Div createTabViewContainer() {
		Div tabViewContainer = new Div();
		tabViewContainer.setId("tab-view-container");
		return tabViewContainer;
	}

	/**
	 * @return An contentOverlay element. It covers the other content when the
	 *         mainMenu element is visible in drawer mode. The contentOverlay
	 *         element's visibility, position and size is set with javascript
	 *         (see reflect-resize.html)
	 */
	private Div createOverlay() {
		Div contentOverlay = new Div();
		new StyleBuilder().setPosition(Position.FIXED).setDisplay(Display.NONE).setBackground(BLACK_WITH_OPACITY)
				.setZIndex(Z_INDEX_CONTENT_OVERLAY).setCursor(Cursor.POINTER).setFor(contentOverlay);
		contentOverlay.setId("overlay");
		return contentOverlay;
	}

	/**
	 * @return An header element. The header element is a placeholder (a
	 *         container) for the title bar with tab headers. The header element
	 *         and its children will be repositioned and resized with javascript
	 *         (see reflect-resize.html)
	 */
	private HorizontalLayout createHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setId("header");
		header.getElement().removeAttribute("theme");
		new StyleBuilder().setBackground(Color.LIGHT_GRAY).setOverflow(Overflow.HIDDEN).setWidth(100, SizeUnit.PERCENT)
				.setHeight(55, SizeUnit.PX).setPadding(5, 5, 0, 5).setZIndex(Z_INDEX_HEADER).setFor(header);

		Button mainMenuButton = createMainMenuButton();// TODO improve button
														// and icon appearance
		Span title = createTitle();
		Tabs tabs = createTabs();
		Button contextMenuButton = createContextMenuButton();// TODO improve
																// button and
																// icon
																// appearance

		header.add(mainMenuButton, title, tabs, contextMenuButton);
		header.setVerticalComponentAlignment(Alignment.CENTER, mainMenuButton, title, contextMenuButton);
		header.setVerticalComponentAlignment(Alignment.END, tabs);
		return header;
	}

	private Button createContextMenuButton() {
		Button contextMenuButton = new Button(new Icon(VaadinIcon.ELLIPSIS_DOTS_V));
		contextMenuButton.setId("context-menu-button");
		new StyleBuilder().setColor(Color.WHITE).setPosition(Position.ABSOLUTE).setRight(0, SizeUnit.PX)
				.setFor(contextMenuButton);
		return contextMenuButton;
	}

	private Button createMainMenuButton() {
		Button mainMenuButton = new Button(new Icon(VaadinIcon.MENU));
		mainMenuButton.setId("main-menu-button");
		new StyleBuilder().setColor(Color.WHITE).setFor(mainMenuButton);
		return mainMenuButton;
	}

	private Tabs createTabs() {
		Tab tab1 = new Tab("Tab one");
		LoremIpsumTabView view1 = new LoremIpsumTabView(tab1);

		Tab tab2 = new Tab("Tab two");
		LoremIpsumTabView view2 = new LoremIpsumTabView(tab2);
		view2.setVisible(false);

		Tab tab3 = new Tab("Tab three");
		LoremIpsumTabView view3 = new LoremIpsumTabView(tab3);
		view3.setVisible(false);

		tabsToViews = new HashMap<>();
		tabsToViews.put(tab1, view1);
		tabsToViews.put(tab2, view2);
		tabsToViews.put(tab3, view3);

		tabsBar = new Tabs(tab1, tab2, tab3);
		tabsBar.setId("tab-headers");

		tabViewContainer.add(view1, view2, view3);
		selectedTabView = Stream.of(view1).collect(Collectors.toSet());

		tabsBar.addSelectedChangeListener(this::onSelectTab);
		return tabsBar;
	}

	private Span createTitle() {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationInfo applicationInfo = reflectionProvider.getApplicationInfo();
		Span title = new Span(applicationInfo.getDisplayName());
		title.setTitle(applicationInfo.getDescription());
		title.setId("title");
		new StyleBuilder().setColor(Color.WHITE).setOverflow(Overflow.HIDDEN).setFontSize(16, SizeUnit.PT)
				.setFont("Roboto").setFor(title);
		return title;
	}

	public TabViewController getTabViewController() {
		return tabViewController;
	}

	public void onRemoveTab(TabView view) {
		// TODO Auto-generated method stub

	}

	public void onAddTab(TabView newView) {
		Tab tab = new Tab(newView.getViewTitle());
		tabsToViews.put(tab, newView);
		tabsBar.add(tab);
		tabViewContainer.add(newView);
		onSelectTabView(newView);
	}

	/**
	 * Called by remote GUI when user clicks on a tab
	 * @param event of user clicking on tab
	 */
	public void onSelectTab(Tabs.SelectedChangeEvent event) {
		Tab selectedTab = tabsBar.getSelectedTab();
		TabView selectedView = tabsToViews.get(selectedTab);
		onSelectTabView(selectedView);
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
		for (Tab tab : tabsToViews.keySet()) {
			if (tabsToViews.get(tab)==selectedView) {
				tabsBar.setSelectedTab(tab);
				break;
			}
		};
		selectedView.onViewActivate();
		resizeChildernOnBrowser();
	}

	private void resizeChildernOnBrowser() {
		UI.getCurrent().getPage().executeJavaScript("updateGui()");
	}

}
