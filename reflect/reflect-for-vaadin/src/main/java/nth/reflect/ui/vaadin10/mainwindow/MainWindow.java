package nth.reflect.ui.vaadin10.mainwindow;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import javafx.scene.control.TreeItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.item.ItemFactory;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.ui.vaadin10.css.Cursor;
import nth.reflect.ui.vaadin10.css.Display;
import nth.reflect.ui.vaadin10.css.Overflow;
import nth.reflect.ui.vaadin10.css.Position;
import nth.reflect.ui.vaadin10.css.SizeUnit;
import nth.reflect.ui.vaadin10.css.StyleBuilder;

public class MainWindow extends Div   {

	private static final long serialVersionUID = -1026778643991244247L;
	private static final int Z_INDEX_HEADER = 9998;
	private static final int Z_INDEX_MAIN_MENU = 9999;
	private static final int Z_INDEX_CONTENT_OVERLAY = Z_INDEX_MAIN_MENU;
	private static final Color BLACK_WITH_OPACITY = new Color(0f, 0f, 0f, 0.5f);
	private final UserInterfaceContainer userInterfaceContainer;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer=userInterfaceContainer;
		HorizontalLayout header = createHeader();
		Div mainMenu = createMainMenu();
		Div content = createContent();
		Div contentOverlay = createContentOverlay();
		add(header, mainMenu, content, contentOverlay);
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
		new StyleBuilder().setProperty("border-right", "1px solid lightgray").setZIndex(Z_INDEX_MAIN_MENU).setBackground(Color.WHITE )
				.setFor(mainMenu);
		
		Grid<Item> mainMenuContent=createMainMenuContent();
		mainMenu.add(mainMenuContent);

		return mainMenu;
	}

	private Grid<Item> createMainMenuContent() {
		Grid<Item> grid=new Grid<>();
		new StyleBuilder().setOverflow(Overflow.AUTO).setFor(grid);
		grid.setItems(getMainMenuItems());
		grid.addColumn(Item::getText);
		return grid;
	}

	private List<Item> getMainMenuItems() {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);

		List<MethodOwnerItem> serviceObjectItems = ItemFactory
				.createMainMenuItems(userInterfaceContainer);

		List<Item> items=new ArrayList<>();
		//TODO wait for Vaadin10 to release a TreeGrid (current state: planned but no time line)
		for (MethodOwnerItem serviceObjectItem:serviceObjectItems) {
			items.add(serviceObjectItem);
			for (Item actionMethodItem:serviceObjectItem.getChildren()) {
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
	private Div createContent() {
		Div content = new Div();
		content.setId("content");
		Div loremIpsumText = createLoremIpsumText();
		content.add(loremIpsumText);
		return content;
	}

	/**
	 * @return An contentOverlay element. It covers the other content when the
	 *         mainMenu element is visible in drawer mode. The contentOverlay
	 *         element's visibility, position and size is set with javascript
	 *         (see reflect-resize.html)
	 */
	private Div createContentOverlay() {
		Div contentOverlay = new Div();
		new StyleBuilder().setPosition(Position.FIXED).setDisplay(Display.NONE).setBackground(BLACK_WITH_OPACITY)
				.setZIndex(Z_INDEX_CONTENT_OVERLAY).setCursor(Cursor.POINTER).setFor(contentOverlay);
		contentOverlay.setId("content-overlay");
		return contentOverlay;
	}

	private Div createLoremIpsumText() {
		Div loremIpsumText = new Div();
		new StyleBuilder().setOverflow(Overflow.AUTO).setPadding(20).setFor(loremIpsumText);
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < 30; i++) {
			text.append(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ut ante dolor. Integer sit amet efficitur lorem. Etiam scelerisque velit et elementum pulvinar. Phasellus eu nisi vel dui faucibus cursus ac luctus ipsum. Nam ullamcorper ex nisl. Donec lobortis sem ac bibendum ultrices. Ut ullamcorper facilisis consequat. Integer et lacus id urna venenatis placerat. Fusce gravida velit et maximus viverra.");
			// Random rand = new Random();
			// int totalNrOfNewLines = rand.nextInt(3);
			// for (int nrOfNewLines = 0; nrOfNewLines < totalNrOfNewLines;
			// ++nrOfNewLines) {
			// text.append("<br/>");
			// }
		}
		loremIpsumText.setText(text.toString());
		return loremIpsumText;
	}

	/**
	 * @return An header element. The header element is a placeholder (a
	 *         container) for the title bar with tab headers. The header element and its children 
	 *         will be repositioned and resized with javascript (see
	 *         reflect-resize.html)
	 */
	private HorizontalLayout createHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setId("header");
		header.getElement().removeAttribute("theme");
		new StyleBuilder().setBackground(Color.LIGHT_GRAY).setOverflow(Overflow.HIDDEN).setWidth(100, SizeUnit.PERCENT).setHeight(55, SizeUnit.PX)
				.setPadding(5, 5, 0, 5).setZIndex(Z_INDEX_HEADER).setFor(header);

		Button mainMenuButton = createMainMenuButton();//TODO improve button and icon appearance
		Span title = createTitle();
		Tabs tabs = createTabs();
		Button contextMenuButton = createContextMenuButton();//TODO improve button and icon appearance

		header.add(mainMenuButton, title, tabs, contextMenuButton);
		header.setVerticalComponentAlignment(Alignment.CENTER, mainMenuButton, title,  contextMenuButton);
		header.setVerticalComponentAlignment(Alignment.END, tabs);
		return header;
	}

	private Button createContextMenuButton() {
		Button contextMenuButton = new Button(new Icon(VaadinIcon.ELLIPSIS_DOTS_V));
		contextMenuButton.setId("context-menu-button");
		new StyleBuilder().setColor(Color.WHITE).setPosition(Position.ABSOLUTE).setRight(0,SizeUnit.PX ) .setFor(contextMenuButton);
		return contextMenuButton;
	}

	private Button createMainMenuButton() {
		Button mainMenuButton = new Button(new Icon(VaadinIcon.MENU));
		mainMenuButton.setId("main-menu-button");
		new StyleBuilder().setColor(Color.WHITE).setFor(mainMenuButton);
		return mainMenuButton;
	}

	private Tabs createTabs() {
		Tab tab1 = new Tab("VaadinView one");
		Tab tab2 = new Tab("VaadinView two");
		Tab tab3 = new Tab("VaadinView three");
		Tabs tabs = new Tabs(tab1, tab2, tab3);
		tabs.setId("tab-headers");
		return tabs;
	}

	private Span createTitle() {
		Span title = new Span("Reflect for Vaadin10"+LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
		title.setId("title");
		new StyleBuilder().setColor(Color.WHITE).setOverflow(Overflow.HIDDEN).setFontSize(16, SizeUnit.PT).setFont("Roboto").setFor(title) ;
		return title;
	}

}
