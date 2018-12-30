package nth.reflect.fw.javafx.control.button;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.ReflectJavaFxControl;
import nth.reflect.fw.javafx.control.fonticon.FontIcon;
import nth.reflect.fw.javafx.control.fonticon.FontIconName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.component.button.ButtonStyle;
import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.ReflectColorName;

/**
 * {@link ReflectJavaFxControl} button in the content color, see
 * {@link ReflectApplicationForJavaFX#getContentColor()
 * 
 * @author nilsth
 *
 */
public class ContentButton extends JFXButton implements ReflectJavaFxControl {

	public ContentButton() {
		super();
		addStyleClass();
	}

	public ContentButton(String text) {
		super(text);
		addStyleClass();
	}

	public ContentButton(FontIconName fontIconName) {
		super();
		addStyleClass();
		setFontIconName(fontIconName);
	}

	public ContentButton(Item item) {
		super();
		addStyleClass();
		setText(item.getText());
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

	public ContentButton(String text, Node node) {
		super(text, node);
		addStyleClass();
	}

	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(ContentButton.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentButton.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_20())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND()).setMinHeight(ButtonStyle.MIN_HEIGHT)
				.setFont(MaterialFont.getRobotoMedium(ButtonStyle.FONT_SIZE));
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentButton.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_20());

		// focused
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentButton.class).appendFocused()).getProperties()
				.setBackground(ReflectColorName.ACCENT.BACKGROUND()).setTextFill(ReflectColorName.ACCENT.FOREGROUND());

		// rippler color
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentButton.class).appendChild("jfx-rippler"))
				.getProperties().put("-jfx-rippler-fill", ReflectColorName.CONTENT.BACKGROUND_12());
		// icon color
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentButton.class).appendChild(FontIcon.class))
				.getProperties().setFill(ReflectColorName.CONTENT.FOREGROUND());
	}

}
