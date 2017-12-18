package nth.reflect.javafx.control.itemtreelist;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.fonticonurl.FontIconUrl;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * TODO Bold text for {@link MethodOwnerItem}s <br>
 * TODO TreeCell<Item> to use the visibility of {@link Item#isVisible()}
 * 
 * @author nilsth
 *
 */
public class RfxItemTreeCell extends TreeCell<Item> implements RfxControl {

	private static final int FONTSIZE = 14;
	private static final String SERVICE_OBJECT_SUFFIX = "service-object";
	private static final String SERVICE_METHOD_SUFFIX = "service-method";

	public RfxItemTreeCell() {
		setDisclosureNode(new Text(""));
	}

	@Override
	protected void updateItem(Item item, boolean empty) {
		super.updateItem(item, empty);

		// if (item != null && item instanceof MethodOwnerItem) {

		// TODO
		// getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxMainMenuListCell.class,SERVICE_OBJECT_SUFFIX));
		// setMouseTransparent(true);
		// setFocusTraversable(false);
		// setBackground(RfxColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		// setPrefHeight(40);
		// setPadding(new Insets(0,13,0,13));
		// setFont(RfxFontFactory.create(MaterialFont.getTitle()));
		// focusedProperty().addListener(this::onFocus);
		// } else {//if item is instance of MethodItem)
		//// TODO
		// getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxMainMenuListCell.class,SERVICE_METHOD_SUFFIX));
		//// setMouseTransparent(false);
		//// setFocusTraversable(true);
		// setBackground(RfxColorFactory.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		// setPrefHeight(40);
		// setPadding(new Insets(0,13,0,40));
		// setFont(RfxFontFactory.create(MaterialFont.getSubHeading()));
		// focusedProperty().addListener(this::onFocus);
		// }

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item.getText());
			setIcon(item.getIconURL());
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
			// /reflect-core/src/main/java/nth/introspect/layer5provider/reflection/behavior/icon/Icon.java

		} catch (MalformedURLException e) {

		}
	}

	// public static void appendStyleGroups(RfxStyleSheet styleSheet,
	// MaterialStyle materialStyle) {
	// ListStyle listStyle = materialStyle.getListSingleLineStyle();
	//
	// // FIXME why add RfxList.class???
	// styleSheet
	// .addStyleGroup(RfxStyleSelector.createFor(RfxList.class)
	// .append(RfxItemTreeCell.class, SERVICE_OBJECT_SUFFIX))
	// .getProperties().setBackground(listStyle.getBackgroundColor())
	// .setCellSize(listStyle.getMinHeight())
	// .setPadding(0, listStyle.getPaddingRight(), 0,
	// listStyle.getPaddingLeft())
	// .setFont(listStyle.getTitleFont());
	// // TODO color
	//
	// styleSheet
	// .addStyleGroup(RfxStyleSelector.createFor(RfxList.class)
	// .append(RfxItemTreeCell.class, SERVICE_OBJECT_SUFFIX).appendFilled()
	// .appendSelected().appendFocused())
	// .getProperties()
	// .setBackground(new nth.introspect.ui.style.basic.Color(0, 0, 0, 15));//
	// -fx-background-color:
	// // rgba(0,0,0,0.06)
	// //
	// .setBackground(listStyle.getSelectedBackGroundColor()).setTextFill(listStyle.getTextColor());
	//
	// // TODO text color
	//
	// // FIXME why add RfxList.class???
	//
	// styleSheet
	// .addStyleGroup(
	// RfxStyleSelector.createFor(RfxItemTreeCell.class, SERVICE_METHOD_SUFFIX))
	// .getProperties().setPadding(0, 0, 0, listStyle.getIndent())
	// .setBackground(listStyle.getBackgroundColor()).setCellSize(listStyle.getMinHeight())
	// .setFont(listStyle.getSubHeadingFont())
	// .setPadding(0, 0, 0, listStyle.getIndent() + listStyle.getPaddingLeft());
	//
	// // TODO text color
	//
	// styleSheet
	// .addStyleGroup(
	// RfxStyleSelector.createFor(RfxItemTreeCell.class, SERVICE_METHOD_SUFFIX)
	// .appendFilled().appendSelected().appendFocused())
	// .getProperties()
	// .setBackground(new nth.introspect.ui.style.basic.Color(0, 0, 0, 15));//
	// -fx-background-color:
	// // rgba(0,0,0,0.06)
	// // .setBackground(listStyle.getSelectedBackGroundColor());//TODO
	// // TODO text color
	//
	// }

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		//TODO add RfxItemTree.class in style selector
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(TreeCell.class)).getProperties()
				.setFont(MaterialFont.getRobotoRegular(FONTSIZE))
				.setBackground("transparent");
		//TODO add RfxItemTree.class in style selector
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(TreeCell.class).appendSelected())
				.getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.FOREGROUND3());

	}

}
