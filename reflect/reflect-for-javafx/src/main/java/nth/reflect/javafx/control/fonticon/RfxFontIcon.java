package nth.reflect.javafx.control.fonticon;

import javafx.scene.text.Text;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.style.RfxStyleSheet;

//See http://stackoverflow.com/questions/33423549/colored-icon-font-in-javafx

/**
 * RfxContentButton rfxButton=new RfxContentButton(); rfxButton.setGraphic(new
 * RfxFontIcon(FontAwesomeIconName.CLONE,16)); <br>
 * icon color can be set with css, e.g.: .rfx-primary-button > .rfx-font-icon {-fx-fill: -rfx-color-primary-foreground1;} 
 * 
 * @author nilsth
 *
 */
public class RfxFontIcon extends Text implements RfxControl {

	public RfxFontIcon(String fontFamilyName, Character unicodeCharacter, int sizeInPixels) {
		super();
		setText(Character.toString(unicodeCharacter));
		
		addStyleClass();
		
		String styleProperties = new RfxStyleProperties()
				.setFont(fontFamilyName)
				.setFontSize(sizeInPixels)
				.toString();
		setStyle(styleProperties);
	}
	
	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxFontIcon.class));
	}

	public RfxFontIcon(RfxFontIconName fontIconName, int sizeInPixels) {
		this(fontIconName.getFontFamilyName(), fontIconName.getCharacter(), sizeInPixels);
	}

}
