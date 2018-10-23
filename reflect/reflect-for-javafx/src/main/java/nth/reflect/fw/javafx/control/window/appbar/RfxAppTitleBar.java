package nth.reflect.fw.javafx.control.window.appbar;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.style.RfxStyleProperties;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.MaterialFont;

/**
 * Title bar as part of the {@link RfxAppBar} which is only visible if the {@link RfxWindow} has sufficient height (see {@link RfxWindow#getExtraHighBinding()}
 * @author nilsth
 *
 */
public class RfxAppTitleBar extends HBox {
	public static final int BAR_HEIGHT = 38;
	
	public RfxAppTitleBar(UserInterfaceContainer userInterfaceContainer) {
		
		initHeightBinding(userInterfaceContainer);

		Label titleLabel = createTitleLabel(userInterfaceContainer);
		getChildren().add(titleLabel);
	}

	private void initHeightBinding(UserInterfaceContainer userInterfaceContainer) {
		RfxWindow rfxWindow=userInterfaceContainer.get(RfxWindow.class);
		BooleanBinding windowExtraHighBinding = rfxWindow.getExtraHighBinding();
		
		visibleProperty().bind(windowExtraHighBinding);
		NumberBinding heightBinding = Bindings.when(windowExtraHighBinding).then(BAR_HEIGHT)
				.otherwise(0);
		minHeightProperty().bind(heightBinding);
		maxHeightProperty().bind(heightBinding);
	}

	private Label createTitleLabel(UserInterfaceContainer userInterfaceContainer) {
		String title = getTitle(userInterfaceContainer);
		Label titleLabel = new Label(title);
		String style = new RfxStyleProperties()
				.setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setAlignment(Pos.CENTER_LEFT).setFont(MaterialFont.getRobotoMedium(20))
				.setPadding(0, 0, 0, 16).toString();
		titleLabel.setStyle(style);
		return titleLabel;
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
