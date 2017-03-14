package nth.reflect.javafx.control.list;

import javax.swing.ViewportLayout;

import javafx.scene.control.ListView;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ListStyle;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;

/**
 * Reflect List for JavaFX with Google Material Design style
 * 
 * TODO http://stackoverflow.com/questions/26537548/javafx-listview-with-touch-events-for-scrolling-up-and-down 
 * @author nilsth
 *
 */
		
public class RfxList<T> extends ListView<T> {

	public RfxList() {
		new RfxVerticalFlingScroller(this);
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxList.class));
	}


	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		ListStyle listStyle = materialStyle.getListSingleLineStyle();

		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class)).getProperties()
			.setBorderWidth(0)
			.setBackground(listStyle.getBackgroundColor())
			.setPadding(0);
		
		
//		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class).append(ListCell.class))
//			.setBackground(listStyle.getBackgroundColor())
//			.setMinHeight(listStyle.getMinHeight())
//			.setPadding(listStyle.getPaddingLeft());//TODO
//		//TODO font size and color
//	
//		
//		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class).append(ListCell.class).appendFilled().appendSelected().appendFocused())
//			.setBackground(new nth.introspect.ui.style.basic.Color(255,0,0));//TODO
//		//TODO text color
		
	}
		
	
	
}
