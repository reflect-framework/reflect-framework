package nth.reflect.fw.javafx.control.button;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import nth.reflect.fw.gui.component.button.ButtonStyle;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.ReflectJavaFxControl;
import nth.reflect.fw.javafx.control.fonticon.FontIcon;
import nth.reflect.fw.javafx.control.fonticon.FontIconName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;

/**
 * {@link ReflectJavaFxControl} button in the content color, see
 * {@link ReflectApplicationForJavaFX#getContentColor()
 * 
 * @author nilsth
 *
 */
public class ContentBottomToolbarButton extends JFXButton implements ReflectJavaFxControl {

	public ContentBottomToolbarButton() {
		super();
		addStyleClass();
	}

	public ContentBottomToolbarButton(String text) {
		super(text.toUpperCase());
		addStyleClass();
	}

	public ContentBottomToolbarButton(FontIconName fontIconName) {
		super();
		addStyleClass();
		setFontIconName(fontIconName);
	}

	public ContentBottomToolbarButton(Item item) {
		super();
		addStyleClass();
		setText(item.getText().toUpperCase());
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				item.getAction().run();
			}
		});
	}

	private void setFontIconName(FontIconName fontIconName) {
		if (fontIconName == null) {
			setGraphic(null);
		} else {
			setGraphic(new FontIcon(fontIconName, ButtonStyle.ICON_SIZE));
		}
	}

	public ContentBottomToolbarButton(String text, Node node) {
		super(text, node);
		addStyleClass();
	}

	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(ContentBottomToolbarButton.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet
				.addStyleGroup(StyleSelector.createFor(ContentBottomToolbarButton.class))
				.getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_20())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setMinHeight(ButtonStyle.MIN_HEIGHT)
				.setPadding(0, ButtonStyle.PADDING_SIDE, 0, ButtonStyle.PADDING_SIDE)
				.setFont(MaterialFont.getRobotoMedium(ButtonStyle.FONT_SIZE));
		// focused
		styleSheet
				.addStyleGroup(StyleSelector.createFor(ContentButton.class).appendFocused())
				.getProperties()
				.setBackground(ReflectColorName.ACCENT.BACKGROUND())
				.setTextFill(ReflectColorName.ACCENT.FOREGROUND());
		// rippler color
		styleSheet
				.addStyleGroup(StyleSelector.createFor(ContentBottomToolbarButton.class).appendChild("jfx-rippler"))
				.getProperties()
				.put("-jfx-rippler-fill", ReflectColorName.CONTENT.BACKGROUND());

		// icon color
		styleSheet
				.addStyleGroup(StyleSelector.createFor(ContentBottomToolbarButton.class).appendChild(FontIcon.class))
				.getProperties()
				.setFill(ReflectColorName.CONTENT.FOREGROUND());
	}

}
