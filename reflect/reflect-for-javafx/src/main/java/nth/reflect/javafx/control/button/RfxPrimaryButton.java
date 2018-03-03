package nth.reflect.javafx.control.button;

import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;


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
				.setBackground(MaterialColorSetCssName.PRIMARY.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1())
				.setPadding(8, 16, 8, 16);
		//rippler color
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)
						.appendChild("jfx-rippler"))
				.getProperties()
				.put("-jfx-rippler-fill", MaterialColorSetCssName.PRIMARY.BACKGROUND_HIGHLIGHTED());
		//icon color
		styleSheet
		.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)
				.appendChild(RfxFontIcon.class))
		.getProperties()
		.setFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1());
		//TODO font???
	}
}
