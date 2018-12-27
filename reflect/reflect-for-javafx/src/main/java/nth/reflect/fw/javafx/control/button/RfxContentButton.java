package nth.reflect.fw.javafx.control.button;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.RfxControl;
import nth.reflect.fw.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.fw.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.ReflectColorName;

/**
 * {@link RfxControl} button in the content color, see
 * {@link ReflectApplicationForJavaFX#getContentColor()
 * 
 * @author nilsth
 *
 */
public class RfxContentButton extends JFXButton implements RfxControl {

	private static final int ICON_SIZE = 17;
	private static final int MIN_HEIGHT = 32;
	private static final int FONT_SIZE = 14;

	public RfxContentButton() {
		super();
		addStyleClass();
	}

	public RfxContentButton(String text) {
		super(text);
		addStyleClass();
	}

	public RfxContentButton(RfxFontIconName fontIconName) {
		super();
		addStyleClass();
		setFontIconName(fontIconName);
	}

	public RfxContentButton(Item item) {
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

	private void setFontIconName(RfxFontIconName fontIconName) {
		if (fontIconName == null) {
			setGraphic(null);
		} else {
			setGraphic(new RfxFontIcon(fontIconName, ICON_SIZE));
		}
	}

	public RfxContentButton(String text, Node node) {
		super(text, node);
		addStyleClass();
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxContentButton.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_20())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND()).setMinHeight(MIN_HEIGHT)
				.setFont(MaterialFont.getRobotoMedium(FONT_SIZE));
		// rippler color
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class).appendChild("jfx-rippler"))
				.getProperties().put("-jfx-rippler-fill", ReflectColorName.CONTENT.BACKGROUND_12());
		// icon color
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class).appendChild(RfxFontIcon.class))
				.getProperties().setFill(ReflectColorName.CONTENT.FOREGROUND());
	}

}
