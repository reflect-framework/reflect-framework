package nth.reflect.fw.gui.style;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

/**
 * {@link ReflectFramework} applications with a graphical user interface use 3
 * main colors:
 * <ul>
 * <li>PrimaryBackgroundColor: A color that is prominent visible, e.g.: the
 * background color of the application tool bar</li>
 * <li>AccentBackGroundColor: A color that is used for thinks that need
 * attention (i.e. important buttons or controls that have focus)</li>
 * <li>ContentBackgroundColor: A color used for the background of the content.
 * This color should preferably either be very light (e.g. White) or very dark
 * (e.g.Black)</li>
 * </ul>
 * 
 * <p>
 * <a href="https://material.io/design/">Google's Material Design</a> recommends
 * to use colors from color palettes that they recommend. See <a href=
 * "https://material.io/design/color/the-color-system.html#color-usage-palettes">Google's
 * Material Design Palettes</a> for more information.
 * </p>
 * 
 * <p>
 * Each color can be derived into a {@link ReflectColorSet}.
 * {@insert ReflectColorSet}
 * </p>
 * 
 * <p>
 * You can override the default colors by extending the
 * {@link GraphicalUserinterfaceController} and overriding the
 * {@link GraphicalUserinterfaceController#getColors()} method. You then must
 * return this {@link GraphicalUserinterfaceController} by overriding the
 * {@link ReflectApplication#getUserInterfaceControllerClass()}
 * </p>
 */

public class ReflectColors {

	private final ReflectColorSet primaryColors;
	private final ReflectColorSet accentColors;
	private final ReflectColorSet contentColors;
	private final ReflectColorSet errorColors;

	/**
	 * Convenience method to get the {@link ReflectColors} form the
	 * {@link UserInterfaceContainer}
	 */
	public static ReflectColors getFrom(UserInterfaceContainer userInterfaceContainer) {
		GraphicalUserInterfaceApplication application = userInterfaceContainer
				.get(GraphicalUserInterfaceApplication.class);
		return application.getColors();
	}

	/**
	 * @param primaryBackgroundColor
	 *            a color that is prominent visible, e.g.: the background color
	 *            of the application tool bar. This color should preferably be a
	 *            darkish color (<50%) and often represents the main color of a
	 *            brand.
	 * @param accentBackGroundColor
	 *            a color that is used for things that need attention (i.e.
	 *            important buttons or controls that have focus)
	 * @param contentBackgroundColor
	 *            a color used for the background of the content. This color
	 *            should preferably either be very light (e.g. White) or very
	 *            dark (e.g.Black)
	 */
	public ReflectColors(Color primaryBackgroundColor, Color accentBackGroundColor, Color contentBackgroundColor) {
		this.primaryColors = new ReflectColorSet(primaryBackgroundColor);
		this.accentColors = new ReflectColorSet(accentBackGroundColor);
		this.contentColors = new ReflectColorSet(contentBackgroundColor);
		this.errorColors = new ReflectColorSet(MaterialColorPalette.error());
	}

	public ReflectColorSet getPrimaryColors() {
		return primaryColors;
	}

	public ReflectColorSet getAccentColors() {
		return accentColors;
	}

	public ReflectColorSet getContentColors() {
		return contentColors;
	}

	public ReflectColorSet getErrorColors() {
		return errorColors;
	}

}
