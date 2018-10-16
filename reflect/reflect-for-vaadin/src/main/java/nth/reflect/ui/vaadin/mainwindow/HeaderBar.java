package nth.reflect.ui.vaadin.mainwindow;

import java.awt.Color;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.appinfo.ApplicationInfo;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.Position;
import nth.reflect.ui.vaadin.css.SizeUnit;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class HeaderBar extends HorizontalLayout {

	private static final long serialVersionUID = -3897762168514590194L;
	private final UserInterfaceContainer userInterfaceContainer;

	/**
	 * The {@link HeaderBar} HTML element is a placeholder (a container) for the
	 * title bar with tab headers. The {@link HeaderBar} element and its children
	 * will be repositioned and resized with javascript (see main-window.js)
	 */

	public HeaderBar(UserInterfaceContainer userInterfaceContainer, MainWindow mainWindow) {
		this.userInterfaceContainer = userInterfaceContainer;
		setId("headerBar");
		getElement().removeAttribute("theme");
		new StyleBuilder().setBackground(Color.LIGHT_GRAY).setOverflow(Overflow.HIDDEN).setWidth(100, SizeUnit.PERCENT)
				.setHeight(55, SizeUnit.PX).setPadding(5, 5, 0, 5).setZIndex(MainWindow.Z_INDEX_HEADER).setFor(this);

		Button mainMenuButton = createMainMenuButton();
		
		Span title = createTitle();
		TabHeaderBar tabHeaderBar = mainWindow.getTabHeaderBar();
		Button contextMenuButton = createContextMenuButton();
		

		add(mainMenuButton, title, tabHeaderBar, contextMenuButton);
//		setVerticalComponentAlignment(Alignment.CENTER, mainMenuButton, title, contextMenuButton);
//		setVerticalComponentAlignment(Alignment.END, tabHeaderBar);

	}
	
	// TODO improve button and icon appearance
	private Button createContextMenuButton() {
		Button contextMenuButton = new Button(new Icon(VaadinIcon.ELLIPSIS_DOTS_V));
		contextMenuButton.setId("contextMenuButton");
		new StyleBuilder().setColor(Color.WHITE).setPosition(Position.ABSOLUTE).setRight(0, SizeUnit.PX)
				.setFor(contextMenuButton);
		return contextMenuButton;
	}

	// TODO improve button and icon appearance
	private Button createMainMenuButton() {
		Button mainMenuButton = new Button(new Icon(VaadinIcon.MENU));
		mainMenuButton.setId("mainMenuButton");
		mainMenuButton.getElement().setAttribute("onclick", "toggleMainMenu()");
		new StyleBuilder().setColor(Color.WHITE).setFor(mainMenuButton);
		return mainMenuButton;
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
}
