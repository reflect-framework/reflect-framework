package nth.reflect.javafx.control.toolbar;

import java.net.MalformedURLException;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ApplicationToolbarStyle;
import nth.introspect.ui.style.control.ApplicationToolbarTitleStyle;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

public class RfxApplicationToolbar extends HBox implements RfxControl {//ToolBar {


	public RfxApplicationToolbar( UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxApplicationToolbar.class));
		
//		ToolbarIconStyle iconStyle = materialStyle.getTabToolbarIconStyle();
//		addMenuButton(iconStyle);
//
//		if (MenuType.EMBEDDED == materialStyle.getMenuStyle().getMenuType()) {
//			addMenuHeader(materialStyle.getApplicationToolbarTitleStyle());
//		}
//		
		addApplicationTitle( userInterfaceContainer);
		addSpacer();
	}


	private void addMenuButton(ToolbarIconStyle iconStyle) throws MalformedURLException {
//		RfxApplicationToolbarMenuButton menuButton = new RfxApplicationToolbarMenuButton();
//		getChildren().add(menuButton);
	}

	private void addMenuHeader(ApplicationToolbarTitleStyle titleStyle) {
//		RfxApplicationToolbarTitle title = new RfxApplicationToolbarTitle(); 
//		getChildren().add(title);
	}

	private void addApplicationTitle( UserInterfaceContainer userInterfaceContainer) {
		RfxApplicationToolbarTitle title = new RfxApplicationToolbarTitle(userInterfaceContainer);
		getChildren().add(title);
	}
	
	private void addSpacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		getChildren().add(spacer);
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		ApplicationToolbarStyle toolbarStyle=materialStyle.getApplicationToolbarStyle();
		
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbar.class)).getProperties()
		.setBackground(toolbarStyle.getBackgroundColor())
		.setHeight(toolbarStyle.getHeight())
		.setMinWidth(300)
		.setPadding(0)
		.setAlignment(Pos.CENTER_LEFT);
	}

}
