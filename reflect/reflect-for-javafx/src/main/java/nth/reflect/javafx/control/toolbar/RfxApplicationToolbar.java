package nth.reflect.javafx.control.toolbar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ApplicationToolbarStyle;
import nth.introspect.ui.style.control.ApplicationToolbarTitleStyle;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxBackgroundFactory;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Reflect Application Toolbar for JavaFX with Google Material Design style
 * 
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbar extends HBox {//ToolBar {
	private RfxWindow window;
	private double xOffset;
	private double yOffset;

	// TODO
	// http://stackoverflow.com/questions/18173956/how-to-drag-undecorated-window

	public RfxApplicationToolbar(MaterialStyle materialStyle, RfxWindow window, UserInterfaceContainer userInterfaceContainer) {
		this.window = window;
		initStyleProperties(materialStyle.getApplicationToolbarStyle());
		
		ToolbarIconStyle iconStyle = materialStyle.getTabToolbarIconStyle();
		addMenuButton(iconStyle);
		addApplicationTitle(materialStyle.getApplicationToolbarTitleStyle(), userInterfaceContainer);
		addSpacer();

		RfxApplicationToolbarMinimizeWindowButton minimizeButton = new RfxApplicationToolbarMinimizeWindowButton(
				window, iconStyle);
		getChildren().add(minimizeButton);

		RfxApplicationToolbarResizeWindowButton resizeButton = new RfxApplicationToolbarResizeWindowButton(
				window, iconStyle);
		getChildren().add(resizeButton);

		RfxApplicationToolbarCloseWindowButton closeButton = new RfxApplicationToolbarCloseWindowButton(iconStyle);
		getChildren().add(closeButton);

		setOnMouseClicked((mouseEvent) -> onMouseClicked(mouseEvent, resizeButton));
		setOnMouseDragged((mouseEvent) -> setOnMouseDragged(mouseEvent));
	}

	private void initStyleProperties(ApplicationToolbarStyle toolbarStyle) {
		setMinWidth(300);
		setBackground(RfxBackgroundFactory.fill(toolbarStyle.getBackgroundColor()));
		setHeight(toolbarStyle.getHeight());
		setPadding(Insets.EMPTY);
		setAlignment(Pos.CENTER_LEFT);
	}

	private void setOnMouseDragged(MouseEvent event) {
		window.setX(event.getScreenX() + xOffset);
		window.setY(event.getScreenY() + yOffset);
	}

	private void onMouseClicked(MouseEvent event,
			RfxApplicationToolbarResizeWindowButton resizeWindoButton) {
		if (event.getClickCount() == 2) {
			resizeWindoButton.fire();
		} else {
			xOffset = window.getX() - event.getScreenX();
			yOffset = window.getY() - event.getScreenY();
		}
	}

//	private void initStyleClassName() {
//		getStyleClass().add(RfxStyleGroup.getStyleClassName(RfxApplicationToolbar.class));
//	}

	private void addMenuButton(ToolbarIconStyle iconStyle) {
		RfxApplicationToolbarMenuButton menuButton = new RfxApplicationToolbarMenuButton(iconStyle);
		getChildren().add(menuButton);
	}

	private void addApplicationTitle(ApplicationToolbarTitleStyle titleStyle, UserInterfaceContainer userInterfaceContainer) {
		RfxApplicationToolbarTitle title = new RfxApplicationToolbarTitle(titleStyle, userInterfaceContainer);
		getChildren().add(title);
	}

	private void addSpacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		getChildren().add(spacer);
	}

//	public static List<RfxStyleGroup> getStyleGroups(MaterialStyle materialStyle) {
//		ApplicationToolbarStyle applicationToolbarStyle = materialStyle
//				.getApplicationToolbarStyle();
//
//		List<RfxStyleGroup> styleGroups = new ArrayList<>();
//
//		RfxStyleGroup style = new RfxStyleGroup(RfxApplicationToolbar.class);
//		
//
//		return styleGroups;
//	}
}
