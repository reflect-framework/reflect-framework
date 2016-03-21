package nth.reflect.javafx.control.toolbar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.style.control.ApplicationToolbarTitleStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxFontFactory;

/**
 * Reflect Application Toolbar for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarTitle extends Label {

	public RfxApplicationToolbarTitle(ApplicationToolbarTitleStyle style,
			UserInterfaceContainer UserInterfaceContainer) {
		initStyleProperties(style);
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

	private void initStyleProperties(ApplicationToolbarTitleStyle style) {
		setTextFill(RfxColorFactory.create(style.getTextColor()));
		setFont(RfxFontFactory.create(style.getFont()));
		setAlignment(Pos.CENTER_LEFT);
		VBox.setVgrow(this, Priority.ALWAYS);
		setPadding(new Insets(0, 16, 0, 16));// TODO get from titleStyle
	}

}
