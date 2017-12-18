package nth.introspect.ui.style;

public enum MaterialColorSetCssName {
	PRIMARY, 
	ACCENT, 
	CONTENT;

	/**
	 * @return CSS name for {@link MaterialColorSet#getForeground1()}
	 */
	public String FOREGROUND1() {
		return getColorSetCssName("foreground1");
	}

	/**
	 * @return CSS name for {@link MaterialColorSet#getForeground2()}
	 */
	public String FOREGROUND2() {
		return getColorSetCssName("foreground2");
	}

	/**
	 * @return CSS name for {@link MaterialColorSet#getForeground3()}
	 */
	public String FOREGROUND3() {
		return getColorSetCssName("foreground2");
	}

	/**
	 * @return CSS name for {@link MaterialColorSet#getBackground()}
	 */
	public String BACKGROUND() {
		return getColorSetCssName("background");
	}

	/**
	 * @return CSS name for {@link MaterialColorSet#getBackgroundHighLighted()}
	 */
	public String BACKGROUND_HIGHLIGHTED() {
		return getColorSetCssName("background-heighlighted");
	}
	
	/**
	 * @return CSS name for {@link MaterialColorSet#getBackgroundHighLighted()}
	 */
	public String TRANSPARENT() {
		return "transparent";
	}

	

	private String getColorSetCssName(String colorName) {
		StringBuilder cssName = new StringBuilder();
		cssName.append("-rfx-color-");
		cssName.append(this.name().toLowerCase());
		cssName.append("-");
		cssName.append(colorName.toLowerCase());
		return cssName.toString();
	}

}
