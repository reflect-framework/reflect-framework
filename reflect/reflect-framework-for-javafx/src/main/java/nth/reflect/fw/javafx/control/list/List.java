package nth.reflect.fw.javafx.control.list;

import javafx.scene.control.ListView;
import nth.reflect.fw.gui.component.table.TableStyle;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.ReflectJavaFxControl;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;

/**
 * Reflect List for JavaFX with Google Material Design style
 * 
 * TODO
 * http://stackoverflow.com/questions/26537548/javafx-listview-with-touch-events-for-scrolling-up-and-down
 * 
 * @author nilsth
 *
 */

public class List<T> extends ListView<T> implements ReflectJavaFxControl {

	public List() {
		// setBackground(ColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		getStyleClass().add(StyleSheet.createStyleClassName(List.class));
		// setPadding(Insets.EMPTY);
	}

	// public static void appendStyleGroups(StyleSheet styleSheet,
	// ReflectColors materialStyle) {
	//
	// styleSheet.addStyleGroup(StyleSelector.createFor(List.class)).getProperties()
	// .setBorderWidth(0)
	// .setBackground(MaterialColors.getContentColorSet().getBackground())
	// .setPadding(0);

	// styleSheet.addStyleGroup(StyleSelector.createFor(List.class).append(ListCell.class))
	// .setBackground(listStyle.getBackgroundColor())
	// .setMinHeight(listStyle.getMinHeight())
	// .setPadding(listStyle.getPaddingLeft());//TODO
	// //TODO font size and color
	//
	//
	// styleSheet.addStyleGroup(StyleSelector.createFor(List.class).append(ListCell.class).appendFilled().appendSelected().appendFocused())
	// .setBackground(new nth.reflect.fw.gui.style.basic.Color(255,0,0));//TODO
	// //TODO text color

	// }

	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(List.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(List.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND()).setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setFont(MaterialFont.getRobotoRegular(TableStyle.FONT_SIZE)).setPadding(0);

		// TODO set row background color transparent, not intermediate
		// TODO set focused row
		// styleSheet.addStyleGroup(StyleSelector.createFor(List.class,"cell").appendSelected()).getProperties().setBackground(ReflectColorName.ACCENT.BACKGROUND());
		// TODO remove focus border
		// .setProperty("-fx-background-color",
		// "transparent,-fx-box-border,-fx-control-inner-background")
		// .setProperty("-fx-background-insets", "-1.4, 0, 1")
		// .setProperty("-fx-background-radius", "1.4, 0, 0")
		// .setProperty("-fx-padding", "1");

	}

}
