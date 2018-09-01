package nth.reflect.fw.javafx.control.window.appbar;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.When;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.RfxViewContainer;
import nth.reflect.fw.javafx.control.button.RfxPrimaryButton;
import nth.reflect.fw.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.fw.javafx.control.style.RfxFontFactory;
import nth.reflect.fw.javafx.control.style.RfxStyleProperties;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.MaterialFont;

/**
 * TODO: this is an experimental Class to try and replace {@link RfxTabButton}
 * so we can add a close button
 * 
 * @author nilsth
 *
 */
public class RfxTabHeaderContent extends HBox {

	private static final int MIN_HEIGHT = 34;
	private static final int MAX_TEXT_WIDTH = 400;// 264;
	private static final int PADDING = 12;
	private final View tab;
	private final RfxWindow rfxWindow;

	public RfxTabHeaderContent(RfxWindow rfxWindow, View tab) {
		this.rfxWindow = rfxWindow;
		this.tab = tab;
		setFocusTraversable(true);

		BooleanBinding tabSelected = rfxWindow.getSelectedTabProperty().isEqualTo(tab);
		When whenTabSelected = Bindings.when(tabSelected);
		ObservableValue<String> styleBinding = createStyleBinding(whenTabSelected);
		styleProperty().bind(styleBinding);

		Label label = createLabel(tab);
		getChildren().add(label);

		RfxPrimaryButton closeButton = createCloseButton(tabSelected);
		getChildren().add(closeButton);

		setOnMouseClicked(this::onHeaderClicked);
	}


	private Label createLabel(View tab) {
		Label label = new Label(tab.getViewTitle());
		label.setFont(RfxFontFactory.create(MaterialFont.getRobotoMedium(14)));
		String style = new RfxStyleProperties()
				.setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1())
				.setAlignment(Pos.CENTER_LEFT).toString();
		label.setStyle(style);
		label.setMinHeight(MIN_HEIGHT);
		label.setMaxWidth(MAX_TEXT_WIDTH);
		return label;
	}

	private RfxPrimaryButton createCloseButton(BooleanBinding tabSelected) {
		RfxPrimaryButton closeButton = new RfxPrimaryButton(FontAwesomeIconName.CLOSE);
		closeButton.setOnAction(this::onCloseButtonPressed);
		closeButton.visibleProperty().bind(tabSelected);
		closeButton.managedProperty().bind(tabSelected);
		return closeButton;

	}
	private void onHeaderClicked(MouseEvent event) {
		selectTab();
	}

	private void selectTab() {
		UserInterfaceContainer userInterfaceContainer = rfxWindow.getUserInterfaceContainer();
		RfxUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		RfxViewContainer viewContainer = userInterfaceController.getViewController();
		viewContainer.setSelectedView(tab);
	}
	private void onCloseButtonPressed(ActionEvent event) {
		closeTab();
		event.consume();
	}

	private void closeTab() {
		UserInterfaceContainer userInterfaceContainer = rfxWindow.getUserInterfaceContainer();
		RfxUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		RfxViewContainer viewContainer = userInterfaceController.getViewController();
		viewContainer.removeView(tab);
	}

	private ObservableValue<String> createStyleBinding(When whenTabSelected) {
		String selectedTabStyle = new RfxStyleProperties()
				.setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1())
				.setPadding(0, 0, 0, PADDING).setMinHeight(MIN_HEIGHT).setBorderWidth(0, 0, 4, 0)
				.setBorderColor(MaterialColorSetCssName.ACCENT.BACKGROUND())
				.setFont(MaterialFont.getRobotoMedium(14)).toString();
		String unselectedTabStyle = new RfxStyleProperties()
				.setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND2())
				.setPadding(0, PADDING, 0, PADDING).setMinHeight(MIN_HEIGHT).setBorderWidth(0, 0, 4, 0)
				.setBorderColor(MaterialColorSetCssName.PRIMARY.BACKGROUND())
				.setFont(MaterialFont.getRobotoMedium(14)).toString();
		ObservableValue<String> styleBinding = whenTabSelected.then(selectedTabStyle)
				.otherwise(unselectedTabStyle);
		return styleBinding;
	}

}
