package nth.reflect.javafx.control.list;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ListStyle;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * Reflect List for JavaFX with Google Material Design style
 * 
 * 
 * @author nilsth
 *
 */
public class RfxList extends ListView {

	public RfxList(MaterialStyle materialStyle) {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxList.class));
	}


	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		ListStyle listStyle = materialStyle.getListSingleLineStyle();

		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class))
			.setBorderWidth(0)
			.setBackground(listStyle.getBackgroundColor())
			.setPadding(0);
		
		
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class).append(ListCell.class))
			.setBackground(listStyle.getBackgroundColor())
			.setMinHeight(listStyle.getMinHeight())
			.setPadding(listStyle.getPaddingLeft());//TODO
		//TODO font size and color
	
		
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxList.class).append(ListCell.class).appendFilled().appendSelected().appendFocused())
			.setBackground(new nth.introspect.ui.style.basic.Color(255,0,0));//TODO
		//TODO text color
		
	}
		
	
	
}
