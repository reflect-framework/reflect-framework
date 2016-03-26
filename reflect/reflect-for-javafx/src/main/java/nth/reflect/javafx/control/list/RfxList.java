package nth.reflect.javafx.control.list;

import javafx.scene.control.ListView;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ListStyle;
import nth.reflect.javafx.control.style.RfxBackgroundFactory;
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
		//initStyleProperies(listStyle);
//		setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//			@Override
//			public ListCell<String> call(ListView<String> list) {
//				return new RfxListCell(listStyle);
//			}
//		});
System.out.println(RfxStyleSheet.getStyleClass(RfxList.class));
		getStyleClass().setAll(RfxStyleSheet.getStyleClass(RfxList.class));
	}

	private void initStyleProperies(ListStyle listStyle) {
		setBackground(RfxBackgroundFactory.fill(listStyle.getBackgroundColor()));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		ListStyle listStyle = materialStyle.getListSingleLineStyle();

		styleSheet.addStyleGroup(RfxList.class)
			.setBorderWidth(0)
			.setBackground(listStyle.getBackgroundColor())
			.setPadding(0);
		
		
		styleSheet.addStyleGroup(RfxList.class, "list-cell")
			.setBackground(listStyle
			.getBackgroundColor())
			.setPadding(listStyle.getPaddingLeft());//TODO
		//TODO font size and color
	
		
		styleSheet.addStyleGroup(RfxList.class, "list-cell", RfxStyleSheet.FILLED, RfxStyleSheet.SELECTED, RfxStyleSheet.FOCUSED)
			.setBackground(new nth.introspect.ui.style.basic.Color(255,0,0));//TODO
		//TODO text color
		
	}
		
	
	
}
