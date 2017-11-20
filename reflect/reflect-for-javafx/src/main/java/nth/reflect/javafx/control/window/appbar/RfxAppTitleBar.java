package nth.reflect.javafx.control.window.appbar;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Title bar as part of the {@link RfxAppBar} which is only visible if the {@link RfxWindow} has sufficient height (see {@link RfxWindow#getExtraHighBinding()}
 * @author nilsth
 *
 */
public class RfxAppTitleBar extends HBox {
	public static final int BAR_HEIGHT = 38;
	
	public RfxAppTitleBar(UserInterfaceContainer userInterfaceContainer) {
		
		RfxWindow rfxWindow=userInterfaceContainer.get(RfxWindow.class);
		BooleanBinding windowExtraHighBinding = rfxWindow.getExtraHighBinding();
		
		setMinHeight(BAR_HEIGHT);
		
		visibleProperty().bind(windowExtraHighBinding);
		NumberBinding heightBinding = Bindings.when(windowExtraHighBinding).then(BAR_HEIGHT)
				.otherwise(0);
		minHeightProperty().bind(heightBinding);
		maxHeightProperty().bind(heightBinding);

		String title = getTitle(userInterfaceContainer);
		Label titleLabel = new Label(title);
		String style = new RfxStyleProperties()
				.setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1())
				.setAlignment(Pos.CENTER_LEFT).setFont(MaterialFont.getTitle())
				.setPadding(0, 0, 0, 16).toString();
		titleLabel.setStyle(style);

		getChildren().add(titleLabel);
	}

	private String getTitle(UserInterfaceContainer userInterfaceContainer) {
			ReflectionProvider reflectionProvider = userInterfaceContainer
					.get(ReflectionProvider.class);
			ReflectApplicationForJavaFX application = userInterfaceContainer
					.get(ReflectApplicationForJavaFX.class);
			ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
			String title = applicationInfo.getDisplayName();
			return title;
	}

	
}
