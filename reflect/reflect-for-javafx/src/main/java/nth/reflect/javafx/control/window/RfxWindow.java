
package nth.reflect.javafx.control.window;

import java.net.MalformedURLException;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.mainmenu.RfxMainMenuView;
import nth.reflect.javafx.control.tabpane.RfxTabBarPane;

public class RfxWindow extends StackPane implements RfxControl {

	// private static final int MENU_WIDTH = 0;
	private final RfxTabBarPane tabPane;
	// private StackPane contentPlaceHolder = new StackPane();
	// private RfxApplicationToolbar applicationToolbar;
	private final BooleanBinding extraHighBinding;
	private final BooleanBinding extraWideBinding;
	private final BooleanProperty mainMenuVisibleProperty;
	private RfxMainMenuView menuPane;
	public static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	public static final double WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;
	private static final int MENU_SLIDE_ANIMATION_DURATION = 500;


	
	public RfxWindow(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		super();

		extraHighBinding = heightProperty().greaterThan(WINDOW_FAIRLY_HIGH_BINDING);
		extraWideBinding = widthProperty().greaterThan(WINDOW_FAIRLY_WIDE_BINDING);
		mainMenuVisibleProperty=createMainMenuVisibleProperty();

		userInterfaceContainer.add(this);

		// primaryStage.initStyle(StageStyle.TRANSPARENT);
		setMinWidth(300);
		setMinHeight(500);
		// setPickOnBounds(false);
		// TODO initStyleProperties()
		// TODO initControlls()

		menuPane = new RfxMainMenuView(userInterfaceContainer);
		
		tabPane = new RfxTabBarPane(userInterfaceContainer, menuPane);
		getChildren().add(tabPane);

	}



	private BooleanProperty createMainMenuVisibleProperty() {
		BooleanProperty mainMenuVisibleProperty= new BooleanPropertyBase(true) {
			
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
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					onMainMenuShow();
				} else {
					onMainMenuHide();
				}
			}
		});
		return mainMenuVisibleProperty;
	}



	public RfxTabBarPane getTabPane() {
		return tabPane;
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