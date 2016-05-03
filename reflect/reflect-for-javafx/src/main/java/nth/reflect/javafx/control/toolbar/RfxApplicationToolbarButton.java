package nth.reflect.javafx.control.toolbar;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.introspect.ui.style.fonticonurl.FontIconUrl;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * Reflect Application Toolbar Button (icons) for JavaFX with Google Material
 * Design style
 * 
 * @author nilsth
 *
 */

public class RfxApplicationToolbarButton extends Button {

	// TODO RfxFlatButton
	// TODO RfxRaisedButton

	public RfxApplicationToolbarButton(String fontIconUrl) throws MalformedURLException {
		super();
		 setIcon(fontIconUrl);
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxApplicationToolbarButton.class));
	}

	public void setIcon(String fontIconUrl) throws MalformedURLException {
		FontIconUrl iconUrl = new FontIconUrl(fontIconUrl);
		Font f = Font.loadFont(
				MaterialFont.getFontAwesome().getUrl().toExternalForm(),
				32);
		setFont(f);
		String character=iconUrl.getCharacter();
		setText(character);
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {

		ToolbarIconStyle iconStyle = materialStyle.getTabToolbarIconStyle();
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbarButton.class))
				.setBorderWidth(0).setHeight(iconStyle.getSize()).setPadding(iconStyle.getPadding())
				.setBackground(null).setTextFill(iconStyle.getColor())
				.setFont(materialStyle.getApplicationToolbarIconStyle().getFont()) //TODO how to do other fonts than FontAwesomeUrl?
				.setFontSize(materialStyle.getApplicationToolbarIconStyle().getSize());

		styleSheet.addStyleGroup(
				RfxStyleSelector.createFor(RfxApplicationToolbarButton.class).appendPressed())
				.setBackground(iconStyle.getPressedColor());

		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbarButton.class)
				.append(ImageView.class)).setTextFill(iconStyle.getColor());
	}

}
