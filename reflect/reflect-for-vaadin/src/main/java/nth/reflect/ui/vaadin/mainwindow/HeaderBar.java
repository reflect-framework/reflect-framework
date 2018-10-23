package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.appinfo.ApplicationInfo;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.SizeUnit;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class HeaderBar extends HorizontalLayout {

	private static final long serialVersionUID = -3897762168514590194L;
	private final UserInterfaceContainer userInterfaceContainer;

	/**
	 * The {@link HeaderBar} HTML element is a placeholder (a container) for the
	 * title bar with tab headesrs. The {@link HeaderBar} element and its
	 * children will be repositioned and resized with javascript (see
	 * main-window.js)
	 */

	public HeaderBar(UserInterfaceContainer userInterfaceContainer, MainWindow mainWindow) {
		this.userInterfaceContainer = userInterfaceContainer;
		setId("headerBar");
	
		getElement().removeAttribute("theme");
		new StyleBuilder().setBackground(ReflectColorName.PRIMARY.BACKGROUND()).setOverflow(Overflow.HIDDEN).setWidth(100, SizeUnit.PERCENT)
				.setHeight(55, SizeUnit.PX).setPadding(5, 5, 0, 5).setZIndex(MainWindow.Z_INDEX_HEADER).setFor(this);

		System.out.println(	new StyleBuilder().setBackground(ReflectColorName.PRIMARY.BACKGROUND()).setOverflow(Overflow.HIDDEN).setWidth(100, SizeUnit.PERCENT)
				.setHeight(55, SizeUnit.PX).setPadding(5, 5, 0, 5).setZIndex(MainWindow.Z_INDEX_HEADER).toString());
		Button mainMenuButton = createMainMenuButton();

		Span title = createTitle();
		TabHeaderBar2 tabHeaderBar = mainWindow.getTabHeaderBar();

		add(mainMenuButton, title, tabHeaderBar);
		// setVerticalComponentAlignment(Alignment.CENTER, mainMenuButton,
		// title, contextMenuButton);
		// setVerticalComponentAlignment(Alignment.END, tabHeaderBar);

	}

	// TODO improve button and icon appearance
	private Button createMainMenuButton() {
		Button mainMenuButton = new Button(new Icon(VaadinIcon.MENU));
		mainMenuButton.setId("mainMenuButton");
		mainMenuButton.getElement().setAttribute("onclick", "toggleMainMenu()");
		new StyleBuilder().setColor(ReflectColorName.PRIMARY.FOREGROUND()).setFor(mainMenuButton);
		return mainMenuButton;
	}

	private Span createTitle() {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationInfo applicationInfo = reflectionProvider.getApplicationInfo();
		Span title = new Span(applicationInfo.getDisplayName());
		title.setTitle(applicationInfo.getDescription());
		title.setId("title");
		new StyleBuilder().setColor(ReflectColorName.PRIMARY.FOREGROUND()).setOverflow(Overflow.HIDDEN).setFontSize(16, SizeUnit.PT)
				.setFont("Roboto").setFor(title);
		return title;
	}
}
