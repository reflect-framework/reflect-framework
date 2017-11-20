package nth.reflect.javafx.control.button;

import com.jfoenix.controls.JFXRippler;

import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;


/**
 * {@link RfxControl} button in the primary color, see {@link ReflectApplicationForJavaFX#getPrimaryColor()}
 * @author nilsth
 *
 */
public class RfxPrimaryButton extends RfxContentButton implements RfxControl {

	// private static final double ICON_HEIGHT = 34;
	//
	// public RfxPrimaryButton(RfxFontIconName iconName) {
	// super(iconName);
	// setButtonType(ButtonType.FLAT);
	// setColorSet(MaterialColors.getPrimaryColorSet());
	// setMinHeight(ICON_HEIGHT);
	// setMinWidth(ICON_HEIGHT);
	// setPadding(new Insets(8, 16, 8, 16));
	// }

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

		//TODO create a button that only uses the style sheets:  getStylesheets().add(RfxStyleSheet.createStyleClassName(RfxContentButton.class));
//		setColorSet(MaterialColors.getContentColorSet());
//		disabledProperty().addListener((observable, oldValue, newValue) -> {
//			updateColors();
//		});
	}
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)).getProperties()
				.setBackground(MaterialColorSetCssName.PRIMARY.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1());
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(RfxPrimaryButton.class)
						.append(RfxStyleSelector.createFor(JFXRippler.class)))
				.getProperties()
				.put("-fx-rippler-fill", MaterialColorSetCssName.PRIMARY.BACKGROUND_HIGHLIGHTED());
	}
}
