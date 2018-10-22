package nth.reflect.fw.javafx.control.window;

import java.net.MalformedURLException;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.control.RfxControl;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.window.appbar.RfxAppBar;
import nth.reflect.fw.javafx.control.window.appbar.RfxAppButtonBar;
import nth.reflect.fw.javafx.control.window.content.RfxContentPane;
import nth.reflect.fw.javafx.control.window.mainmenu.RfxMainMenuPane;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.tab.Tabs;

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

	private RfxMainMenuPane mainMenuPane;

	public static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	public static final double WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;
	private static final int MENU_SLIDE_ANIMATION_DURATION = 500;
	private RfxAppBar appBar;
	private RfxContentPane contentPane;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Tabs<Tab> tabs;

	public RfxWindow(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		super();
		this.userInterfaceContainer = userInterfaceContainer;
		tabs = getTabs(userInterfaceContainer);
		extraHighBinding = heightProperty().greaterThan(WINDOW_FAIRLY_HIGH_BINDING);
		extraWideBinding = widthProperty().greaterThan(WINDOW_FAIRLY_WIDE_BINDING);
		mainMenuVisibleProperty = createMainMenuVisibleProperty();

		userInterfaceContainer.add(this);

		setMinWidth(300);
		setMinHeight(500);

		contentPane = new RfxContentPane(tabs);
		getChildren().add(contentPane);

		mainMenuPane = new RfxMainMenuPane(userInterfaceContainer);
		getChildren().add(mainMenuPane);

		appBar = new RfxAppBar(userInterfaceContainer, mainMenuPane);
		getChildren().add(appBar);

		mainMenuPane.translateXProperty().addListener(this::onMenuMovingLeftOrRight);
		setOnKeyPressed(createKeyEventHandler());
	}

	private Tabs<Tab> getTabs(UserInterfaceContainer userInterfaceContainer) {
		RfxUserinterfaceController userinterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		Tabs<Tab> tabs = userinterfaceController.getTabs();
		return tabs;
	}

	/**
	 * Hack to move {@link #contentPane} by calling {@link #layoutChildren()}
	 * and to move {@link RfxTabButton}s in the {@link RfxAppButtonBar} by
	 * calling {@link RfxAppButtonBar#requestLayout()} when the
	 * {@link #mainMenuPane} slides in (when shown) or out (when hidden). This
	 * method is called by registering a listener to the
	 * {@link RfxMainMenuPane#translateXProperty()}
	 * 
	 * @param observable
	 * @param oldXValue
	 * @param newXValue
	 */
	public void onMenuMovingLeftOrRight(ObservableValue<? extends Number> observable, Number oldXValue,
			Number newXValue) {
		requestLayout();
		appBar.getButtonBar().requestLayout();
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
				} else if ((event.getCode() == KeyCode.F6 || event.getCode() == KeyCode.TAB) && event.isControlDown()) {
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
		tabs.selectPrevious();
	}

	protected void selectNextTab() {
		tabs.selectNext();
	}

	private void closeSelectedTab() {
		tabs.remove(tabs.getSelected());
	}

	private void toggleMainMenuVisibility() {
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

	/**
	 * Custom layout: {@link #getContentBias()} moves next to
	 * {@link #mainMenuPane} when the {@link #extraWideBinding}==true. <br>
	 * This method is also called when then {@link #mainMenuPane} slides in or
	 * out (see
	 * {@link #onMenuMovingLeftOrRight(ObservableValue, Number, Number)})
	 */
	@Override
	protected void layoutChildren() {
		double width = getWidth();
		double height = getHeight();
		boolean snapToPixel = isSnapToPixel();
		double x = 0;
		double y = 0;

		double appBarHeight = appBar.calculateHeight();
		appBar.resize(width, appBarHeight);
		positionInArea(appBar, x, y, width, appBarHeight, 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
				snapToPixel);
		y += appBarHeight;

		if (extraWideBinding.get()) {
			x = MENU_WIDTH + mainMenuPane.getTranslateX();
		} else {
			x = 0;
		}
		contentPane.resize(width - x, height - y);
		positionInArea(contentPane, x, y, width - x, height - y,
				0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);

		x = 0;
		mainMenuPane.resize(MENU_WIDTH, height - y);
		positionInArea(mainMenuPane, x, y, MENU_WIDTH, height - y,
				0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);
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
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					onMainMenuShow();
				} else if (tabs.size() > 0) {
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
		translate.setNode(mainMenuPane);
		translate.setDuration(Duration.millis(MENU_SLIDE_ANIMATION_DURATION));
		double width = mainMenuPane.getMinWidth();
		translate.setToX(width * -1);
		translate.play();
	}

	private void onMainMenuShow() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(mainMenuPane);
		translate.setDuration(Duration.millis(MENU_SLIDE_ANIMATION_DURATION));
		translate.setToX(0);
		translate.play();
	}

	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}

}