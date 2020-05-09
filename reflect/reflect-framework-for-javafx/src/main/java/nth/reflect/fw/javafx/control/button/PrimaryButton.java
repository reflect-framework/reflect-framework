package nth.reflect.fw.javafx.control.button;

import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.ReflectJavaFxControl;
import nth.reflect.fw.javafx.control.fonticon.FontIcon;
import nth.reflect.fw.javafx.control.fonticon.FontIconName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;

/**
 * {@link ReflectJavaFxControl} button in the primary color, see
 * {@link ReflectApplicationForJavaFX#getPrimaryColor()}
 * 
 * @author nilsth
 *
 */
public class PrimaryButton extends ContentButton implements ReflectJavaFxControl {

	public PrimaryButton(Item item) {
		super(item);
	}

	public PrimaryButton(FontIconName fontIconName) {
		super(fontIconName);
	}

	public PrimaryButton(String text) {
		super(text);
	}

	@Override
	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(PrimaryButton.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(PrimaryButton.class)).getProperties()
				.setBackground(ReflectColorName.PRIMARY.BACKGROUND()).setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setPadding(8, 16, 8, 16);
		// focused
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentButton.class).appendFocused()).getProperties()
				.setBackground(ReflectColorName.ACCENT.BACKGROUND()).setTextFill(ReflectColorName.ACCENT.FOREGROUND());
		// rippler color
		styleSheet.addStyleGroup(StyleSelector.createFor(PrimaryButton.class).appendChild("jfx-rippler"))
				.getProperties().put("-jfx-rippler-fill", ReflectColorName.PRIMARY.BACKGROUND_20());
		// icon color
		styleSheet.addStyleGroup(StyleSelector.createFor(PrimaryButton.class).appendChild(FontIcon.class))
				.getProperties().setFill(ReflectColorName.PRIMARY.FOREGROUND());
		// TODO font???
	}
}
