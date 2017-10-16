
package nth.reflect.javafx.control.window;

import java.net.MalformedURLException;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.DisplayScale;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.basic.Color;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.formview.RfxFormView;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.tabpane.RfxTabBarPane;

public class RfxWindow extends StackPane implements RfxControl {

	// private static final int MENU_WIDTH = 0;
	private final RfxTabBarPane tabPane;
	// private StackPane contentPlaceHolder = new StackPane();
	// private RfxApplicationToolbar applicationToolbar;
	private final BooleanBinding extraHighBinding;
	private final BooleanBinding extraWideBinding;
	public static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	public static final double WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;

	
	public RfxWindow(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		super();

		extraHighBinding = heightProperty().greaterThan(WINDOW_FAIRLY_HIGH_BINDING);
		extraWideBinding = widthProperty().greaterThan(WINDOW_FAIRLY_WIDE_BINDING);

		userInterfaceContainer.add(this);

		// primaryStage.initStyle(StageStyle.TRANSPARENT);
		setMinWidth(300);
		setMinHeight(500);
		// setPickOnBounds(false);
		// TODO initStyleProperties()
		// TODO initControlls()


		tabPane = new RfxTabBarPane(userInterfaceContainer);
		getChildren().add(tabPane);

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

}