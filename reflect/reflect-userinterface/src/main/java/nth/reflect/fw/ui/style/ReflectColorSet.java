package nth.reflect.fw.ui.style;

import nth.reflect.fw.ui.style.basic.Color;

/**
 * A {@link ReflectColorSet} is a group of background and foreground colors,
 * based on a given background color and a predefined set of rules.
 * 
 * @author nilsth
 *
 */
public class ReflectColorSet implements MaterialDesign {

	private final Color backgroundColor;
	private final Color foregroundColor;
	private final Color backgroundColor12;
	private final Color backgroundColor20;

	/**
	 * 
	 * @param backgroundColor
	 *            : the backgroundColor color for a specific theme color. Note
	 *            that you should use predefined colors by the
	 *            {@link MaterialDesign}.
	 */

	public ReflectColorSet(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		if (backgroundColor.isDark()) {
			foregroundColor = Color.WHITE;
			backgroundColor12 = foregroundColor.deriveAlpha(0.12);
			backgroundColor20 = foregroundColor.deriveAlpha(0.20);
		} else {
			foregroundColor = Color.BLACK;
			backgroundColor12 = foregroundColor.deriveAlpha(0.12);
			backgroundColor20 = foregroundColor.deriveAlpha(0.20);
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
	 * @return color of background for buttons and separators. Note do not use
	 *         for mouse over: mouse overs confuses the user what has focus.
	 */
	public Color getBackground12() {
		return backgroundColor12;
	}

	/**
	 * 
	 * @return color of background for when the object is activated by the user
	 *         (pressed\ clicked). Note do not use for mouse over: mouse overs
	 *         confuses the user what has focus.
	 */
	public Color getBackground20() {
		return backgroundColor20;
	}

	/**
	 * @return the primary foreground color i.e. used for the most important
	 *         texts or icons. Note that you should use predefined colors by the
	 *         {@link MaterialDesign}. This color should either be black or
	 *         white (depending on the backgroundColor color) and have a certain
	 *         transparency.
	 */
	public Color getForeground() {
		return foregroundColor;
	}

}
