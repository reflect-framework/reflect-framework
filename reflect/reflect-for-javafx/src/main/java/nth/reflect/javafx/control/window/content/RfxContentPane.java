package nth.reflect.javafx.control.window.content;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxContentPane extends BorderPane {

	public RfxContentPane(UserInterfaceContainer userInterfaceContainer) {
		super();
		setBackground(RfxColorFactory
				.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		RfxWindow window=userInterfaceContainer.get(RfxWindow.class);
		ObjectProperty<View> selectedTabProperty = window.getSelectedTabProperty();
		selectedTabProperty.addListener(this::onSelectedTabChange);
	}
	
	public void onSelectedTabChange(ObservableValue<? extends View> observable, View oldValue, View newValue) {
			setCenter((Node) newValue);
	}
}
