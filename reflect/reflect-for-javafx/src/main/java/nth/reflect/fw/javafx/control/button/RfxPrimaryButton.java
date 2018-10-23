package nth.reflect.fw.javafx.control.button;

import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.RfxControl;
import nth.reflect.fw.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.fw.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.style.ReflectColorName;


/**
 * {@link RfxControl} button in the primary color, see {@link ReflectApplicationForJavaFX#getPrimaryColor()}
 * @author nilsth
 *
 */
public class RfxPrimaryButton extends RfxContentButton implements RfxControl {

	public RfxPrimaryButton(Item item) {
		super(item);
	}

	public RfxPrimaryButton(RfxFontIconName fontIconName) {
		super(fontIconName);
	}

	public RfxPrimaryButton(String text) {
		super(text);
	}

	
	@Override
	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxPrimaryButton.class));
	}
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)).getProperties()
				.setBackground(ReflectColorName.PRIMARY.BACKGROUND())
				.setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setPadding(8, 16, 8, 16);
		//rippler color
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)
						.appendChild("jfx-rippler"))
				.getProperties()
				.put("-jfx-rippler-fill", ReflectColorName.PRIMARY.BACKGROUND_20());
		//icon color
		styleSheet
		.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)
				.appendChild(RfxFontIcon.class))
		.getProperties()
		.setFill(ReflectColorName.PRIMARY.FOREGROUND());
		//TODO font???
	}
}
