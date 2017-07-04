package nth.reflect.javafx.control.tabpane;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.effects.JFXDepthManager;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.RfxView;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.list.mainmenu.RfxItemTreeView;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;

public class RfxTabBarPane extends BorderPane {

	public static final int BAR_HEIGHT = 38;//TODO move to RfxToolBar
	public static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	private static final int WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;
	private final ObservableList<RfxView> tabs;
	private final ObjectPropertyBase<RfxView> selectedTabProperty;
	private final BooleanBinding windowExtraHighBinding;
	private final BooleanBinding windowExtraWideBinding;
	private final RfxTabButtonBar tabButtonBar;
	private RfxMenuAndContentPane menuAndContentPane;

	public RfxTabBarPane(UserInterfaceContainer userInterfaceContainer) {
		tabs = FXCollections.<RfxView>observableArrayList();
		tabs.addListener(this::onTabsChanged);
		selectedTabProperty = new SimpleObjectProperty<>();
		selectedTabProperty.addListener(this::onSelectedTabChanged);
		windowExtraHighBinding = heightProperty().greaterThan(WINDOW_FAIRLY_HIGH_BINDING);
		windowExtraWideBinding = widthProperty().greaterThan(WINDOW_FAIRLY_WIDE_BINDING);

		tabButtonBar = new RfxTabButtonBar(this);
		BorderPane toolBar = createApplicationBar(tabButtonBar);
		setTop(toolBar);

		menuAndContentPane = new RfxMenuAndContentPane(userInterfaceContainer);
		setCenter(menuAndContentPane);
	}

	public void onTabsChanged(Change change) {
		if (tabs.size() == 0) {
			selectedTabProperty.set(null);
			updateTabs();
		} else if (!change.getList().contains(selectedTabProperty.get())) {
			selectNewTab(change);
		} else {
			updateTabs();
		}
	}

	private void updateTabs() {
		tabButtonBar.update(tabs);
		updateContent();
	}

	private void updateContent() {
		RfxView selectedTab = selectedTabProperty.get();
		if (selectedTab==null) {
			menuAndContentPane.clearContentPane();
		} else {
			menuAndContentPane.setContentPane(selectedTab.getContent());
		}
	}

	private void selectNewTab(Change<RfxView> change) {
		while (change.next()) {

			if (change.wasAdded()) {
				List<RfxView> added = change.getAddedSubList();
				RfxView lastAdded = added.get(added.size() - 1);
				selectedTabProperty.set(lastAdded);// this also calls
													// updateTabBar();
			} else if (change.wasRemoved()) {
				int newIndex = tabs.indexOf(selectedTabProperty.get()) - 1;
				if (newIndex < 0) {
					newIndex = 0;
				}
				RfxView selectedTab = change.getList().get(newIndex);
				selectedTabProperty.set(selectedTab);// this also calls
														// updateTabBar();
			} else {
				// mutated or pre-mutated (changed order)
				updateTabs();
			}
		}
	}

	public void onSelectedTabChanged(Observable observable) {
		updateTabs();
	}



	private JFXButton createListButton(String text, EventHandler<ActionEvent> event) {
		RfxButton button = new RfxButton(text);
		button.setButtonType(ButtonType.FLAT);
		button.setColorSet(MaterialColors.getContentColorSet());
		button.setMinHeight(32);
		button.setPadding(new Insets(0, 16, 0, 16));
		button.setOnAction(event);
		return button;
	}

	private BorderPane createApplicationBar(RfxTabButtonBar tabButtonBar) {
		BorderPane toolBar = new BorderPane();

		String style = new RfxStyleProperties()
				.setBackground(MaterialColors.getPrimaryColorSet().getBackground())
				.setMinHeight(BAR_HEIGHT)
				// .setMinWidth(300)
				.setPadding(0).setAlignment(Pos.CENTER_LEFT).toString();
		toolBar.setStyle(style);
		JFXDepthManager.setDepth(toolBar, 1);

		HBox titleBar = createTitleBar();
		toolBar.setTop(titleBar);
		BorderPane buttonBar = createButtonBar(tabButtonBar);
		toolBar.setBottom(buttonBar);

		return toolBar;
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
		menuBar.setMinWidth(MENU_WIDTH);
		
		RfxButton mainMenuButton = createMainMenuButtton();
		menuBar.getChildren().add(mainMenuButton);
		
		return menuBar;
	}

	private HBox createTitleBar() {
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
		Label title = new Label("Application Name");
		String style = new RfxStyleProperties()
				.setTextFill(MaterialColors.getPrimaryColorSet().getForeground1())
				.setAlignment(Pos.CENTER_LEFT).setFont(MaterialFont.getTitle())
				.setPadding(0, 0, 0, 16).toString();
		title.setStyle(style);

		// RfxApplicationToolbarTitle(userInterfaceContainer);
		titlePane.getChildren().add(title);
		return titlePane;
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
		mainMenuButton.setOnAction(this::onMenuButtonAction);
		return mainMenuButton;
	}
	
	public void onMenuButtonAction(ActionEvent event) {
		menuAndContentPane.toggleMenuVisibility();
	}
	
	public ObjectProperty<RfxView> getSelectedTabProperty() {
		return selectedTabProperty;
	}

	public ObservableList<RfxView> getTabs() {
		return tabs;
	}
}
