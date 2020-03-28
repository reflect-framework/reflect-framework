package nth.reflect.fw.javafx.control.mainwindow.appbar;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.mainwindow.MainWindow;
import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;

/**
 * Title bar as part of the {@link AppBar} which is only visible if the
 * {@link MainWindow} has sufficient height (see
 * {@link MainWindow#getExtraHighBinding()}
 * 
 * @author nilsth
 *
 */
public class AppTitleBar extends HBox {

	public AppTitleBar(UserInterfaceContainer userInterfaceContainer) {

		initHeightBinding(userInterfaceContainer);

		Label titleLabel = createTitleLabel(userInterfaceContainer);
		getChildren().add(titleLabel);
	}

	private void initHeightBinding(UserInterfaceContainer userInterfaceContainer) {
		MainWindow mainWindow = userInterfaceContainer.get(MainWindow.class);
		BooleanBinding windowExtraHighBinding = mainWindow.getExtraHighBinding();

		visibleProperty().bind(windowExtraHighBinding);
		NumberBinding heightBinding = Bindings.when(windowExtraHighBinding).then(ApplicationBarStyle.HEIGHT)
				.otherwise(0);
		minHeightProperty().bind(heightBinding);
		maxHeightProperty().bind(heightBinding);
	}

	private Label createTitleLabel(UserInterfaceContainer userInterfaceContainer) {
		String title = getTitle(userInterfaceContainer);
		Label titleLabel = new Label(title);
		String style = new StyleProperties().setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setAlignment(Pos.CENTER_LEFT).setFont(MaterialFont.getRobotoMedium(20)).setPadding(0, 0, 0, 16)
				.toString();
		titleLabel.setStyle(style);
		return titleLabel;
	}

	private String getTitle(UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationClassInfo applicationInfo = reflectionProvider.getApplicationClassInfo();
		String title = applicationInfo.getDisplayName().getTranslation();
		return title;
	}

}
