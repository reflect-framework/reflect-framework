package nth.reflect.javafx.control.toolbar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ApplicationToolbarTitleStyle;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * Reflect Application Toolbar for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarTitle extends Label {

	public RfxApplicationToolbarTitle(UserInterfaceContainer UserInterfaceContainer) {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxApplicationToolbarTitle.class));
		initTitle(UserInterfaceContainer);
	}

	private void initTitle(UserInterfaceContainer userInterfaceContainer) {
		IntrospectApplication application = userInterfaceContainer.get(IntrospectApplication.class);
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
		String title = applicationInfo.getDisplayName();
		setText(title);
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		ApplicationToolbarTitleStyle style = materialStyle.getApplicationToolbarTitleStyle();

		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbarTitle.class))
				.setTextFill(style.getTextColor()).setAlignment(Pos.CENTER_LEFT)
				.setFont(style.getFont());
	}

}
