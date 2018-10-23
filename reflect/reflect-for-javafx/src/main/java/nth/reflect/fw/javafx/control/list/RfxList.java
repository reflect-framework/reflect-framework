package nth.reflect.fw.javafx.control.list;

import javafx.scene.control.ListView;
import nth.reflect.fw.javafx.control.RfxControl;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.MaterialFont;

/**
 * Reflect List for JavaFX with Google Material Design style
 * 
 * TODO
 * http://stackoverflow.com/questions/26537548/javafx-listview-with-touch-events-for-scrolling-up-and-down
 * 
 * @author nilsth
 *
 */

public class RfxList<T> extends ListView<T> implements RfxControl {

	private static final int FONT_SIZE = 14;

	public RfxList() {
		new RfxVerticalFlingScroller(this);
		// setBackground(RfxColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxList.class));
		// setPadding(Insets.EMPTY);
	}

	// public static void appendStyleGroups(RfxStyleSheet styleSheet,
	// ReflectColors materialStyle) {
	//
	// styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class)).getProperties()
	// .setBorderWidth(0)
	// .setBackground(MaterialColors.getContentColorSet().getBackground())
	// .setPadding(0);

	// styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class).append(ListCell.class))
	// .setBackground(listStyle.getBackgroundColor())
	// .setMinHeight(listStyle.getMinHeight())
	// .setPadding(listStyle.getPaddingLeft());//TODO
	// //TODO font size and color
	//
	//
	// styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class).append(ListCell.class).appendFilled().appendSelected().appendFocused())
	// .setBackground(new nth.reflect.fw.ui.style.basic.Color(255,0,0));//TODO
	// //TODO text color

	// }

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxList.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setFont(MaterialFont.getRobotoRegular(FONT_SIZE))
				.setPadding(0);
		
//TODO set row background color transparent, not intermediate
//TODO set focused row		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class,"cell").appendSelected()).getProperties().setBackground(ReflectColorName.ACCENT.BACKGROUND());
//TODO remove focus border
		//				.setProperty("-fx-background-color", "transparent,-fx-box-border,-fx-control-inner-background")
//				.setProperty("-fx-background-insets", "-1.4, 0, 1")
//				.setProperty("-fx-background-radius", "1.4, 0, 0")
//				.setProperty("-fx-padding", "1");

				
		
	}

}
