package nth.reflect.javafx.control.window.content;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxContentPane extends BorderPane {

	public RfxContentPane(UserInterfaceContainer userInterfaceContainer) {
		super();
		addStyleClass();
		RfxWindow window = userInterfaceContainer.get(RfxWindow.class);
		ObjectProperty<View> selectedTabProperty = window.getSelectedTabProperty();
		selectedTabProperty.addListener(this::onSelectedTabChange);
	}

	public void onSelectedTabChange(ObservableValue<? extends View> observable, View oldValue,
			View newValue) {
		setCenter((Node) newValue);
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxContentPane.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentPane.class)).getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1());
	}
}
