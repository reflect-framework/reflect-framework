package nth.introspect.ui.style;

import nth.introspect.ui.style.basic.Color;

/**
 * A {@link MaterialColorSet} is a group of background and foreground colors, based on a given background color and a predefined set of rules.
 * See https://www.google.com/design/spec/style/color.html#
 * 
 * @author nilsth
 *
 */
public class MaterialColorSet implements MaterialDesign {

	private static final double LIGHTER = 1.005;
	private static final double DARKER = 0.8;
	private final Color backgroundColor;
	private final Color foregroundColor1;
	private final Color foregroundColor2;
	private final Color foregroundColor3;
	private final Color backgroundColorHighLighted;

	/**
	 * 
	 * @param backgroundColor
	 *            : the backgroundColor color for a specific theme color. Note
	 *            that you should use predefined colors by the
	 *            {@link MaterialDesign}. See
	 *            https://www.google.com/design/spec/style/color.html#
	 */

	public MaterialColorSet(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		if (backgroundColor.isDark()) {
			backgroundColorHighLighted=backgroundColor.deriveDarknes(LIGHTER);
		} else {
			backgroundColorHighLighted=backgroundColor.deriveDarknes(DARKER);
		}
		if (backgroundColor.isDark()) {
			foregroundColor1 = Color.WHITE.deriveAlpha(1.0);
			foregroundColor2 = Color.WHITE.deriveAlpha(0.70);
			foregroundColor3 = Color.WHITE.deriveAlpha(0.30);
		} else {
			foregroundColor1 = Color.BLACK.deriveAlpha(0.87);
			foregroundColor2 = Color.BLACK.deriveAlpha(0.54);
			foregroundColor3 = Color.BLACK.deriveAlpha(0.38);
		}
	}

	
	/**
	 * @return backgroundColor
	 */
	public Color getBackground() {
		return backgroundColor;
	}

	/**
	 * 
	 * @return color of background for when the object is activated by the user (pressed\ clicked)
	 */
	public Color getBackgroundHighLighted() {
		return backgroundColorHighLighted;
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
