package nth.reflect.fw.javafx.control.itemtreelist;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import nth.reflect.fw.javafx.control.RfxControl;
import nth.reflect.fw.javafx.control.style.RfxStyleProperties;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.view.table.RfxTableView;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.item.HierarchicalItem;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.fonticonurl.FontIconUrl;

/**
 * TODO Bold text for {@link MethodOwnerItem}s <br>
 * TODO TreeCell<Item> to use the visibility of {@link Item#isVisible()}
 * 
 * @author nilsth
 *
 */
public class RfxItemTreeCell extends TreeCell<Item> implements RfxControl {

	private static final int FONTSIZE = 14;
	public static final int ITEM_HEIGHT = 40;
	public static final String FONT_WEIGHT_BOLD = new RfxStyleProperties().setFontWeight(FontWeight.BOLD).toString();
	public static final String FONT_WEIGHT_NORMAL = new RfxStyleProperties().setFontWeight(FontWeight.NORMAL).toString();
	
	public RfxItemTreeCell() {
		setDisclosureNode(new Text(""));
	}

	@Override
	protected void updateItem(Item item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item.getText());
			setIcon(item.getIconURL());
			if (item instanceof HierarchicalItem) {
				setStyle(FONT_WEIGHT_BOLD);
			} else	{
				setStyle(FONT_WEIGHT_NORMAL);
			}				
		}
	}

	private void setIcon(URL iconUrl) {
		try {
			if (iconUrl != null && iconUrl.getProtocol().equals(FontIconUrl.PROTOCOL)) {
				FontIconUrl fontIconUrl = new FontIconUrl(iconUrl);
				Label icon = new Label();
				Font font = Font.loadFont(fontIconUrl.getFontUrl().toString(), 16);
				icon.setFont(font);
				icon.setText(fontIconUrl.getCharacter());
				setGraphic(icon);
			}
			// TODO other types see
			// /reflect-core/src/main/java/nth/reflect/fw/layer5provider/reflection/behavior/icon/Icon.java

		} catch (MalformedURLException e) {

		}
	}
	

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		//TODO add RfxItemTree.class in style selector
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(TreeCell.class)).getProperties()
				.setFont(MaterialFont.getRobotoRegular(FONTSIZE))
				.setMaxHeight(ITEM_HEIGHT)
				.setMinHeight(ITEM_HEIGHT)
				.setBackground("transparent");
		//TODO add RfxItemTree.class in style selector
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(TreeCell.class).appendSelected())
				.getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.FOREGROUND3());

	}

}
