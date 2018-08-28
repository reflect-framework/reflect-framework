package nth.reflect.ui.vaadin10;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.acme.web.shop.product.ProductRepository;
import com.acme.web.shop.product.ProductService;
import com.acme.web.shop.shopingcart.ShoppingCartService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

import javafx.scene.control.TreeItem;
import nth.reflect.example.domain.person.PersonService;
import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.ViewContainer;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.about.DefaultAboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.application.ApplicationUrlProvider;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrlProvider;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrlProvider;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;
import nth.reflect.fw.ui.item.ItemFactory;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.ui.vaadin10.css.Cursor;
import nth.reflect.ui.vaadin10.css.Display;
import nth.reflect.ui.vaadin10.css.Overflow;
import nth.reflect.ui.vaadin10.css.Position;
import nth.reflect.ui.vaadin10.css.SizeUnit;
import nth.reflect.ui.vaadin10.css.StyleBuilder;

/**
 * The main view contains a button and a template element.
 */
@SuppressWarnings("serial")
@HtmlImport("styles/reflect-resize.html")
@HtmlImport("bower_components/font-roboto/roboto.html")
@JavaScript("bower_components/jquery/3.3.1-1/jquery.js") 

@Route("")
public class ReflectApplicationForVaadin extends VerticalLayout  implements ReflectApplication {

	private static final int Z_INDEX_HEADER = 9998;
	private static final int Z_INDEX_MAIN_MENU = 9999;
	private static final int Z_INDEX_CONTENT_OVERLAY = Z_INDEX_MAIN_MENU;
	private static final Color BLACK_WITH_OPACITY = new Color(0f, 0f, 0f, 0.5f);
	private final UserInterfaceContainer userInterfaceContainer;

	public ReflectApplicationForVaadin() {
		userInterfaceContainer=ReflectFramework.launch(this);
		
		new StyleBuilder().setPadding(0).setFor(this);
		getElement().removeAttribute("theme");

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
		
		Grid<VaadinItem> mainMenuContent=createMainMenuContent();
		mainMenu.add(mainMenuContent);

		return mainMenu;
	}

	private Grid<VaadinItem> createMainMenuContent() {
		Grid<VaadinItem> grid=new Grid<>();
		new StyleBuilder().setOverflow(Overflow.AUTO).setFor(grid);
		grid.setItems(getMainMenuItems());
		grid.addColumn(VaadinItem::getName);
		return grid;
	}

	private List<VaadinItem> getMainMenuItems() {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);

		List<MethodOwnerItem> serviceObjectItems = ItemFactory
				.createMainMenuItems(userInterfaceContainer);

		
		//TODO wait for Vaadin10 to release a TrreGrid (current state: planned but no time line)
		List<VaadinItem> vaadinItems=new ArrayList<>();
		for (MethodOwnerItem serviceObjectItem:serviceObjectItems) {
			vaadinItems.add(new VaadinItem(serviceObjectItem.getText()));
			for (Item actionMethodItem:serviceObjectItem.getChildren()) {
				vaadinItems.add(new VaadinItem("-  "+actionMethodItem.getText()));
			}
		}
		return vaadinItems;
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
		Tab tab1 = new Tab("Tab one");
		Tab tab2 = new Tab("Tab two");
		Tab tab3 = new Tab("Tab three");
		Tabs tabs = new Tabs(tab1, tab2, tab3);
		tabs.setId("tab-headers");
		return tabs;
	}

	private Span createTitle() {
		Span title = new Span("Reflect for Vaadin10");
		title.setId("title");
		new StyleBuilder().setColor(Color.WHITE).setOverflow(Overflow.HIDDEN).setFontSize(16, SizeUnit.PT).setFont("Roboto").setFor(title) ;
		return title;
	}

	public ViewContainer<nth.reflect.ui.vaadin10.Tab> getViewContainer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserinterfaceControllerForVaadin.class;
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getAboutProviderClass() {
		return DefaultAboutProvider.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}
	
	
	@Override
	public List<Class<? extends UrlProvider>> getUrlProviderClasses() {
		return Arrays.asList(ClassResourceUrlProvider.class, ApplicationUrlProvider.class, FontIconUrlProvider.class);
	}

	//TODO remove later so other ReflectApplicationForVaadin implementations can implement it
	//TODO remove reflect-example-domain from pom.xml
	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(ProductService.class, ShoppingCartService.class, PersonService.class);
	}

	//TODO remove later so other ReflectApplicationForVaadin implementations can implement it
	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(ProductRepository.class);
	}
	
}
