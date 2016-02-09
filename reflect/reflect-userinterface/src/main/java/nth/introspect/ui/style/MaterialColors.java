package nth.introspect.ui.style;

import java.awt.Color;

/**
 * The {@link MaterialDesign} uses groups of colors, represented by this class.
 * See https://www.google.com/design/spec/style/color.html#
 * 
 * @author nilsth
 *
 */
public class MaterialColors implements MaterialDesign {

	private final Color backgroundColor;
	private final Color foregroundColor1;
	private final Color foregroundColor2;
	private final Color foregroundColor3;

	/**
	 * 
	 * @param backgroundColor
	 *            : the backgroundColor color for a specific theme color. Note
	 *            that you should use predefined colors by the
	 *            {@link MaterialDesign}. See
	 *            https://www.google.com/design/spec/style/color.html#
	 */

	public MaterialColors(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		if (ColorUtil.isDark(backgroundColor)) {
			foregroundColor1 = ColorUtil.transparency(Color.WHITE , 1.00);
			foregroundColor2 = ColorUtil.transparency(Color.WHITE, 0.70);
			foregroundColor3 = ColorUtil.transparency(Color.WHITE, 0.30);
		} else {
			foregroundColor1 = ColorUtil.transparency(Color.BLACK, 0.87);
			foregroundColor2 = ColorUtil.transparency(Color.BLACK, 0.54);
			foregroundColor3 = ColorUtil.transparency(Color.BLACK, 0.38);
		}
	}

	public MaterialColors(String backgroundColorInHex) {
		this(ColorUtil.hex2Color(backgroundColorInHex));
	}

	/**
	 * @return backgroundColor
	 */
	public Color getBackground() {
		return backgroundColor;
	}

	/**
	 * @return the primary foreground color i.e. used for the most important
	 *         texts or icons. Note that you should use predefined colors by the
	 *         {@link MaterialDesign}. This color should either be black or
	 *         white (depending on the backgroundColor color) and have a certain
	 *         transparency. See See
	 *         https://www.google.com/design/spec/style/color.html#
	 */
	public Color getForeground1() {
		return foregroundColor1;
	}

	/**
	 * @return the primary foreground color i.e. used for the less important
	 *         texts or icons. Note that you should use predefined colors by the
	 *         {@link MaterialDesign}. This color should either be black or
	 *         white (depending on the backgroundColor color) and have a certain
	 *         transparency. See See
	 *         https://www.google.com/design/spec/style/color.html#
	 */
	public Color getForeground2() {
		return foregroundColor2;
	}

	/**
	 * @return the primary foreground color i.e. used for the least important or
	 *         disabled texts or icons. Note that you should use predefined
	 *         colors by the {@link MaterialDesign}. This color should either be
	 *         black or white (depending on the backgroundColor color) and have
	 *         a certain transparency. See See
	 *         https://www.google.com/design/spec/style/color.html#
	 */
	public Color getForeground3() {
		return foregroundColor3;
	}

}
