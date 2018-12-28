package nth.reflect.fw.javafx.control.fonticon;

import javafx.scene.text.Text;
import nth.reflect.fw.javafx.control.ReflectJavaFxControl;
import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.javafx.control.style.StyleSheet;

//See http://stackoverflow.com/questions/33423549/colored-icon-font-in-javafx

/**
 * ContentButton rfxButton=new ContentButton(); rfxButton.setGraphic(new
 * FontIcon(FontAwesomeIconName.CLONE,16)); <br>
 * icon color can be set with css, e.g.: .rfx-primary-button > .rfx-font-icon {-fx-fill: -rfx-color-primary-foreground1;} 
 * 
 * @author nilsth
 *
 */
public class FontIcon extends Text implements ReflectJavaFxControl {

	public FontIcon(String fontFamilyName, Character unicodeCharacter, int sizeInPixels) {
		super();
		setText(Character.toString(unicodeCharacter));
		
		addStyleClass();
		
		String styleProperties = new StyleProperties()
				.setFont(fontFamilyName)
				.setFontSize(sizeInPixels)
				.toString();
		setStyle(styleProperties);
	}
	
	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(FontIcon.class));
	}

	public FontIcon(FontIconName fontIconName, int sizeInPixels) {
		this(fontIconName.getFontFamilyName(), fontIconName.getCharacter(), sizeInPixels);
	}

}
