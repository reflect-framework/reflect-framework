package nth.reflect.javafx.control.list;

import javafx.beans.property.SetProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;

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

	public RfxList() {
		new RfxVerticalFlingScroller(this);
		// setBackground(RfxColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxList.class));
		// setPadding(Insets.EMPTY);
	}

	// public static void appendStyleGroups(RfxStyleSheet styleSheet,
	// MaterialStyle materialStyle) {
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
	// .setBackground(new nth.introspect.ui.style.basic.Color(255,0,0));//TODO
	// //TODO text color

	// }

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxList.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class)).getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
				.setFont(MaterialFont.getBody1())
				.setPadding(0);
		
//TODO set row background color transparent, not intermediate
		//TODO set focused row		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class,"cell").appendSelected()).getProperties().setBackground(MaterialColorSetCssName.ACCENT.BACKGROUND());
//TODO remove focus border
		//				.setProperty("-fx-background-color", "transparent,-fx-box-border,-fx-control-inner-background")
//				.setProperty("-fx-background-insets", "-1.4, 0, 1")
//				.setProperty("-fx-background-radius", "1.4, 0, 0")
//				.setProperty("-fx-padding", "1");

				
		
	}

}
