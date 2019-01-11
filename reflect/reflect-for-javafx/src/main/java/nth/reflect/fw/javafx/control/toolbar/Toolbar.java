package nth.reflect.fw.javafx.control.toolbar;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.ReflectJavaFxControl;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;

public abstract class Toolbar extends HBox implements ReflectJavaFxControl {//ToolBar {


	public Toolbar()  {
		getStyleClass().add(StyleSheet.createStyleClassName(Toolbar.class));
	}
	
	public void addSpacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		getChildren().add(spacer);
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		
		styleSheet.addStyleGroup(StyleSelector.createFor(Toolbar.class)).getProperties()
		.setBackground(ReflectColorName.CONTENT.BACKGROUND_20())
		.setHeight(60)
		.setMinWidth(300)
		.setPadding(0)
		.setAlignment(Pos.CENTER_LEFT);
	}
	

}
