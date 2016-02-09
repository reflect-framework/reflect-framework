package nth.introspect.ui.style;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 * See https://www.google.com/design/spec/style/typography.html#typography-typeface
 * 
 * Note that introspect uses the same font size for {@link DisplayDense#DENSE} or {@link DisplayDense#NONE_DENSE}, where as Google's {@link MaterialDesign} has small differences
 */

public class MaterialFont  {

	private static Font robotoLight;
	private static Font robotoRegular;
	private static Font robotoMedium;

	
	static{
		robotoLight=createFont("/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Light.ttf");
		robotoRegular=createFont("/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Regular.ttf");
		robotoMedium=createFont("/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Medium.ttf");
	}

	private static Font createFont(String fontResourcePath) {
		try {
        	InputStream stream = MaterialFont.class.getResourceAsStream(fontResourcePath);
        	Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            stream.close();
            return font;
        } catch (FontFormatException ffe) {
            throw new RuntimeException(ffe);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
	}

	
	
	public static final Font DISPLAY4=robotoLight.deriveFont(112f);
	public static final Font DISPLAY3=robotoRegular.deriveFont(56f);
	public static final Font DISPLAY2=robotoRegular.deriveFont( 45f );
	public static final Font DISPLAY1=robotoRegular.deriveFont( 34f );
	public static final Font HEADLINE=robotoRegular.deriveFont( 24f );
	public static final Font TITLE=robotoMedium.deriveFont( 24f );
	public static final Font SUBHEADING=robotoRegular.deriveFont( 16f );
	public static final Font BODY2=robotoMedium.deriveFont( 14f );
	public static final Font BODY1=robotoRegular.deriveFont( 14f );
	public static final Font CAPTION=robotoRegular.deriveFont( 12f );
	public static final Font BUTTON=robotoMedium.deriveFont( 12f );
	

}
