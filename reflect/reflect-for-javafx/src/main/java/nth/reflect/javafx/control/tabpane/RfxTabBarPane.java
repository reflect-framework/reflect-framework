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
	private static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	private static final int WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;
	private final ObservableList<RfxView> tabs;
	private final ObjectPropertyBase<RfxView> selectedTabProperty;
	private final BooleanBinding windowExtraHighBinding;
	private final BooleanBinding windowExtraWideBinding;
	private final RfxTabButtonBar tabButtonBar;
	private final BorderPane contentPane;
	private final BorderPane menuPane;

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

		menuPane = createMenuPane(userInterfaceContainer);
		contentPane=createContent();
		StackPane menuAndContentPane = createMenuAndContentPane(menuPane, contentPane);
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
			contentPane.setCenter(null);
		} else {
			contentPane.setCenter(selectedTab.getContent());
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

	private StackPane createMenuAndContentPane( BorderPane menuPane, BorderPane contentPane) {
		//BorderPane menuAndContentPane = new BorderPane();
//		menuAndContentPane.setLeft(menuPane);
//		menuAndContentPane.setCenter(contentPane);
//	
		StackPane menuAndContentPane = new StackPane();
		menuAndContentPane.getChildren().add(contentPane);
		menuAndContentPane.getChildren().add(menuPane);
		menuAndContentPane.setAlignment(menuPane, Pos.TOP_LEFT);
		
		return menuAndContentPane;
	}

	private BorderPane createContent() {
		BorderPane content = new BorderPane();
		content.setBackground(
				RfxColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		Label label = new Label("Content");
		content.setCenter(label);
		return content;
	}

	private BorderPane createMenuPane(UserInterfaceContainer userInterfaceContainer) {
		BorderPane menuPane = new BorderPane();
		menuPane.setBackground(
				RfxColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		menuPane.setMinWidth(MENU_WIDTH);
		menuPane.setMaxWidth(MENU_WIDTH);
		javafx.scene.paint.Color lineColor = RfxColorFactory
				.create(MaterialColors.getContentColorSet().getForeground3());
		BorderStroke borderStroke = new BorderStroke(null, lineColor, null, null, null,
				BorderStrokeStyle.SOLID, null, null, null, null, Insets.EMPTY);
		Border border = new Border(borderStroke);
		menuPane.setBorder(border);

		//RfxMainMenuList mainMenuList= new RfxMainMenuList(userInterfaceContainer);
		RfxItemTreeView mainMenuList=new RfxItemTreeView(userInterfaceContainer);
		menuPane.setCenter(mainMenuList);
//		VBox buttonPane = new VBox();
//		buttonPane.setPadding(new Insets(1));
//		buttonPane.getChildren()
//				.add(createListButton("Add tab at begin", this::onAddTabAtBeginButton));
//		buttonPane.getChildren()
//				.add(createListButton("Add tab in middle (random)", this::onAddTabAtMiddleButton));
//		buttonPane.getChildren()
//				.add(createListButton("Add tab at the end", this::onAddTabAtEndButton));
//		buttonPane.getChildren()
//				.add(createListButton("Remove tab at begin", this::onRemoveTabAtBeginButton));
//		buttonPane.getChildren().add(
//				createListButton("Remove tab in middle (random)", this::onRemoveTabAtMiddleButton));
//		buttonPane.getChildren()
//				.add(createListButton("Remove tab at the end", this::onRemoveTabAtEndButton));
//		menuPane.setCenter(buttonPane);
		return menuPane;
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

//	private void onAddTabAtBeginButton(ActionEvent actionEvent) {
//		RfxFormView tab = new RfxFormView();
//		tabs.add(0, tab);
//	}
//
//	private void onAddTabAtMiddleButton(ActionEvent actionEvent) {
//		if (tabs.size() > 1) {
//			Random randomIndexGenerator = new Random();
//			int randomIndex = randomIndexGenerator.nextInt(tabs.size() - 1) + 1;
//			RfxFormView tab = new RfxFormView();
//			tabs.add(randomIndex, tab);
//		}
//	}
//
//	private void onAddTabAtEndButton(ActionEvent actionEvent) {
//		int lastIndex = tabs.size();
//		RfxFormView tab = new RfxFormView();
//		tabs.add(lastIndex, tab);
//	}

//	private void onRemoveTabAtBeginButton(ActionEvent actionEvent) {
//		if (tabs.size() > 0) {
//			tabs.remove(0);
//		}
//	}
//
//	private void onRemoveTabAtMiddleButton(ActionEvent actionEvent) {
//		if (tabs.size() > 2) {
//			Random randomIndexGenerator = new Random();
//			int randomIndex = randomIndexGenerator.nextInt(tabs.size() - 2) + 1;
//			tabs.remove(randomIndex);
//		}
//	}
//
//	private void onRemoveTabAtEndButton(ActionEvent actionEvent) {
//		if (tabs.size() > 0) {
//			int lastIndex = tabs.size() - 1;
//			tabs.remove(lastIndex);
//		}
//
//	}

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
		hideMenu();
	}
	
	/**
	 * See https://stackoverflow.com/questions/31601900/javafx-how-to-create-slide-in-animation-effect-for-a-pane-inside-a-transparent
	 */
	private void hideMenu() {
		//See https://stackoverflow.com/questions/31601900/javafx-how-to-create-slide-in-animation-effect-for-a-pane-inside-a-transparent
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(500));
		if (menuPane.getTranslateX()==0) {
			//move left outside window
			double width = menuPane.getMinWidth();
			translate.setToX(width*-1);	
		} else {
			//move left of window
			translate.setToX(0);	
		}
		translate.play();
	}
	public ObjectProperty<RfxView> getSelectedTabProperty() {
		return selectedTabProperty;
	}

	public ObservableList<RfxView> getTabs() {
		return tabs;
	}
}
