package nth.reflect.fw.gui.style;

public enum ReflectColorName {
	PRIMARY, ACCENT, CONTENT, ERROR;

	/**
	 * @return CSS name for {@link ReflectColorSet#getForeground()}
	 */
	public String FOREGROUND() {
		return getColorSetCssName("foreground");
	}

	/**
	 * @return CSS name for {@link ReflectColorSet#getBackground()}
	 */
	public String BACKGROUND() {
		return getColorSetCssName("background");
	}

	/**
	 * @return CSS name for {@link ReflectColorSet#getBackground12()}
	 */
	public String BACKGROUND_12() {
		return getColorSetCssName("background12");
	}

	/**
	 * @return CSS name for {@link ReflectColorSet#getBackground20()}
	 */
	public String BACKGROUND_20() {
		return getColorSetCssName("background20");
	}

	/**
	 * @return CSS name for a transparent color
	 */
	public String TRANSPARENT() {
		return "transparent";
	}

	private String getColorSetCssName(String colorName) {
		StringBuilder cssName = new StringBuilder();
		cssName.append("reflect-color-");
		cssName.append(this.name().toLowerCase());
		cssName.append("-");
		cssName.append(colorName.toLowerCase());
		return cssName.toString();
	}

}
