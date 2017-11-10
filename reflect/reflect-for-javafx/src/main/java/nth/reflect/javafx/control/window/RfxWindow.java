package nth.reflect.javafx.control.window;

import java.net.MalformedURLException;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.window.appbar.RfxAppBar;
import nth.reflect.javafx.control.window.content.RfxContentPane;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;

/**
 * TODO merge {@link RfxMenuAndContentPane} with {@link RfxWindow}
 * 
 * @author nilsth
 *
 */
public class RfxWindow extends StackPane implements RfxControl {

	private final BooleanBinding extraHighBinding;
	private final BooleanBinding extraWideBinding;
	private final BooleanProperty mainMenuVisibleProperty;
	private final ObservableList<View> tabsProperty;
	private final ObjectPropertyBase<View> selectedTabProperty;

	private RfxMainMenuPane menuPane;

	public static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	public static final double WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;
	private static final int MENU_SLIDE_ANIMATION_DURATION = 500;
	private RfxAppBar appBar;
	private RfxContentPane contentPane;

	public RfxWindow(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		super();

		extraHighBinding = heightProperty().greaterThan(WINDOW_FAIRLY_HIGH_BINDING);
		extraWideBinding = widthProperty().greaterThan(WINDOW_FAIRLY_WIDE_BINDING);
		mainMenuVisibleProperty = createMainMenuVisibleProperty();
		tabsProperty = createTabsPropery();
		selectedTabProperty = createSelectedTabProperty();

		userInterfaceContainer.add(this);

		setMinWidth(300);
		setMinHeight(500);

		appBar = new RfxAppBar(userInterfaceContainer);
		getChildren().add(appBar);

		contentPane = new RfxContentPane(userInterfaceContainer);
		getChildren().add(contentPane);

		menuPane = new RfxMainMenuPane(userInterfaceContainer);
		getChildren().add(menuPane);

		setOnKeyPressed(createKeyEventHandler());
	}

	private EventHandler<KeyEvent> createKeyEventHandler() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.F1) {
					toggleMainMenuVisibility();
					event.consume();
				} else if (event.getCode() == KeyCode.F2) {
					// TODO show tabSelectionPopUp
					// event.consume();
				} else if (event.getCode() == KeyCode.F3) {
					// TODO show find box and set focus to it
					// event.consume();
				} else if (event.getCode() == KeyCode.F4 && event.isControlDown()) {
					closeSelectedTab();
					event.consume();
				} else if ((event.getCode() == KeyCode.F6 || event.getCode() == KeyCode.TAB)
						&& event.isControlDown()) {
					if (event.isShiftDown()) {
						selectPreviousTab();
					} else {
						selectNextTab();
					}
					 event.consume();
				}
			}
		};
	}

	protected void selectPreviousTab() {
		View selectedTab = selectedTabProperty.get();
		int selectedTabIndex = tabsProperty.indexOf(selectedTab);
		int previousTabIndex = selectedTabIndex-1;
		if (previousTabIndex<0) {
			previousTabIndex=tabsProperty.size()-1;
		}
		View previousTab = tabsProperty.get(previousTabIndex);
		selectedTabProperty.set(previousTab);
	}

	protected void selectNextTab() {
		View selectedTab = selectedTabProperty.get();
		int selectedTabIndex = tabsProperty.indexOf(selectedTab);
		int nextTabIndex = selectedTabIndex+1;
		if (nextTabIndex>=tabsProperty.size()) {
			nextTabIndex=0;
		}
		View nextTab = tabsProperty.get(nextTabIndex);
		selectedTabProperty.set(nextTab);
	}

	private void closeSelectedTab() {
		View selectedTab = selectedTabProperty.get();
		tabsProperty.remove(selectedTab);
	}

	private void toggleMainMenuVisibility() {
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

	
	@Override
	protected void layoutChildren() {
		double width = getWidth();
		double height = getHeight();
		boolean snapToPixel = isSnapToPixel();
		double x = 0;
		double y = 0;

		double appBarHeight = appBar.calculateHeight();
		appBar.resize(width, appBarHeight);
		positionInArea(appBar, x, y, width, appBarHeight, 0/* ignore baseline */, Insets.EMPTY,
				HPos.LEFT, VPos.TOP, snapToPixel);
		y += appBarHeight;

		contentPane.resize(width, height - y);
		positionInArea(contentPane, x, y, width, height - y,
				0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);

		menuPane.resize(MENU_WIDTH, height - y);
		positionInArea(menuPane, x, y, MENU_WIDTH, height - y,
				0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);
	}

	private SimpleObjectProperty<View> createSelectedTabProperty() {
		SimpleObjectProperty<View> selectedTabProperty = new SimpleObjectProperty<>();
		return selectedTabProperty;
	}

	private ObservableList<View> createTabsPropery() {
		ObservableList<View> tabsProperty = FXCollections.<View>observableArrayList();
		tabsProperty.addListener(this::onTabsChanged);
		return tabsProperty;
	}

	public void onTabsChanged(Change change) {
		if (tabsProperty.size() == 0) {
			selectedTabProperty.set(null);
		} else  {
			selectNewTab(change);
		}
	}


	private void selectNewTab(Change<View> change) {
		while (change.next()) {

			if (change.wasAdded()) {
				List<View> added = change.getAddedSubList();
				View lastAdded = added.get(added.size() - 1);
				selectedTabProperty.set(lastAdded);
			} else if (change.wasRemoved()) {
				int newIndex = tabsProperty.indexOf(selectedTabProperty.get()) - 1;
				if (newIndex < 0) {
					newIndex = 0;
				}
				View selectedTab = change.getList().get(newIndex);
				selectedTabProperty.set(selectedTab);
			} 
		}
	}


	public ObjectProperty<View> getSelectedTabProperty() {
		return selectedTabProperty;
	}

	public ObservableList<View> getTabsProperty() {
		return tabsProperty;
	}

	private BooleanProperty createMainMenuVisibleProperty() {
		BooleanProperty mainMenuVisibleProperty = new BooleanPropertyBase(true) {

			@Override
			public String getName() {
				return "mainMenuVisibleProperty";
			}

			@Override
			public Object getBean() {
				return this;
			}

		};
		mainMenuVisibleProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
					Boolean newValue) {
				if (newValue) {
					onMainMenuShow();
				} else if (tabsProperty.size()>0) {
					onMainMenuHide();
				}
			}
		});
		return mainMenuVisibleProperty;
	}

	public BooleanBinding getExtraHighBinding() {
		return extraHighBinding;
	}

	public BooleanBinding getExtraWideBinding() {
		return extraWideBinding;
	}

	public BooleanProperty getMainMenuVisibleProperty() {
		return mainMenuVisibleProperty;
	}

	private void onMainMenuHide() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(MENU_SLIDE_ANIMATION_DURATION));
		double width = menuPane.getMinWidth();
		translate.setToX(width * -1);
		translate.play();
	}

	private void onMainMenuShow() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(MENU_SLIDE_ANIMATION_DURATION));
		translate.setToX(0);
		translate.play();
	}

}