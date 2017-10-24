package nth.reflect.javafx.control.tabpane;

import com.jfoenix.effects.JFXDepthManager;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxAppBar extends BorderPane {

	public static final int BAR_HEIGHT = 38;//TODO move to RfxToolBar
	private final BooleanBinding windowExtraHighBinding;
	private final RfxWindow rfxWindow;
	
	public RfxAppBar(UserInterfaceContainer userInterfaceContainer, RfxTabButtonBar tabButtonBar) {
		String style = new RfxStyleProperties()
				.setBackground(MaterialColors.getPrimaryColorSet().getBackground())
				.setMinHeight(BAR_HEIGHT)
				// .setMinWidth(300)
				.setPadding(0).setAlignment(Pos.CENTER_LEFT).toString();
		setStyle(style);
		JFXDepthManager.setDepth(this, 1);

		rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		windowExtraHighBinding = rfxWindow.getExtraHighBinding();
		
		HBox titleBar = createTitleBar(userInterfaceContainer);
		setTop(titleBar);
		BorderPane buttonBar = createButtonBar(tabButtonBar);
		setBottom(buttonBar);

	}
	
	private BorderPane createButtonBar(RfxTabButtonBar tabButtonBar) {
		BorderPane buttonBar = new BorderPane();
		buttonBar.setPadding(new Insets(1));
		// leftButtonPane=
		HBox menuBar = createMenuBar();
		buttonBar.setLeft(menuBar);

		buttonBar.setCenter(tabButtonBar);
		
		HBox rightButtonPane = new HBox();
		rightButtonPane.setMaxHeight(BAR_HEIGHT - 10);
		buttonBar.setRight(rightButtonPane);
		RfxButton tabSelectionButton = createTabSelectionButton();
		rightButtonPane.getChildren().add(tabSelectionButton);
		RfxButton tabMenuButton = createTabMenuButton();
		rightButtonPane.getChildren().add(tabMenuButton);
		return buttonBar;
	}

	private HBox createMenuBar() {
		HBox menuBar=new HBox();
		menuBar.setMinHeight(BAR_HEIGHT-10);
		menuBar.setMinWidth(RfxWindow.MENU_WIDTH);
		
		RfxButton mainMenuButton = createMainMenuButtton();
		menuBar.getChildren().add(mainMenuButton);
		
		return menuBar;
	}

	private HBox createTitleBar(UserInterfaceContainer userInterfaceContainer) {
		HBox titlePane = new HBox();
		titlePane.setMinHeight(BAR_HEIGHT);
		titlePane.setBackground(new Background(new BackgroundFill(
				RfxColorFactory.create(MaterialColors.getPrimaryColorSet().getBackground()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		titlePane.visibleProperty().bind(windowExtraHighBinding);
		NumberBinding heightBinding = Bindings.when(windowExtraHighBinding).then(BAR_HEIGHT)
				.otherwise(0);
		titlePane.minHeightProperty().bind(heightBinding);
		titlePane.maxHeightProperty().bind(heightBinding);

		// TODO RfxApplicationToolbarTitle title=new
		String title = getTitle(userInterfaceContainer);
		Label titleLabel = new Label(title);
		String style = new RfxStyleProperties()
				.setTextFill(MaterialColors.getPrimaryColorSet().getForeground1())
				.setAlignment(Pos.CENTER_LEFT).setFont(MaterialFont.getTitle())
				.setPadding(0, 0, 0, 16).toString();
		titleLabel.setStyle(style);

		// RfxApplicationToolbarTitle(userInterfaceContainer);
		titlePane.getChildren().add(titleLabel);
		return titlePane;
	}

	private String getTitle(UserInterfaceContainer userInterfaceContainer) {
			ReflectionProvider reflectionProvider = userInterfaceContainer
					.get(ReflectionProvider.class);
			ReflectApplicationForJavaFX application = userInterfaceContainer
					.get(ReflectApplicationForJavaFX.class);
			ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
			String title = applicationInfo.getDisplayName();
			return title;
	}

	private RfxApplicationToolbarButton createTabMenuButton() {
		RfxApplicationToolbarButton tabMenuButton = new RfxApplicationToolbarButton(
				FontAwesomeIconName.ELLIPSIS_V);
		//tabMenuButton.setOnAction(this::onMenuButtonAction);
		return tabMenuButton;
	}

	

	private RfxApplicationToolbarButton createTabSelectionButton() {
		RfxApplicationToolbarButton tabSelectionButton = new RfxApplicationToolbarButton(
				FontAwesomeIconName.CLONE);
		// menuButton.setOnAction(this::onMenuButtonAction);
		return tabSelectionButton;
	}

	private RfxApplicationToolbarButton createMainMenuButtton() {
		RfxApplicationToolbarButton mainMenuButton = new RfxApplicationToolbarButton(
				FontAwesomeIconName.BARS);
		mainMenuButton.setOnAction(this::onMainMenuButtonAction);
		return mainMenuButton;
	}
	
	public void onMainMenuButtonAction(ActionEvent event) {
		BooleanProperty mainMenuVisibleProperty= rfxWindow.getMainMenuVisibleProperty();
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

}
