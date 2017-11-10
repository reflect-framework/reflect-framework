package nth.reflect.javafx.control.fonticon;

import javafx.scene.text.Text;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.style.RfxStyleProperties;

//See http://stackoverflow.com/questions/33423549/colored-icon-font-in-javafx

/**
 * RfxButton rfxButton=new RfxButton(); rfxButton.setGraphic(new
 * RfxFontIcon(FontAwesomeIconName.CLONE,16));
 * 
 * @author nilsth
 *
 */
public class RfxFontIcon extends Text implements RfxControl {

	public RfxFontIcon(String fontFamilyName, Character unicodeCharacter, int sizeInPixels,
			Color color) {
		super();
		setText(Character.toString(unicodeCharacter));
		String styleProperties = new RfxStyleProperties().setFont(fontFamilyName)
				.setFontSize(sizeInPixels)
				.setFill(color).toString();
		setStyle(styleProperties);
	}

	public RfxFontIcon(RfxFontIconName fontIconName, int sizeInPixels, Color color) {
		this(fontIconName.getFontFamilyName(), fontIconName.getCharacter(), sizeInPixels, color);
	}

}
