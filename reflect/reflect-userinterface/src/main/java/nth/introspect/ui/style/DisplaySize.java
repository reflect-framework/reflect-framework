package nth.introspect.ui.style;

public enum DisplaySize {
	/**
	 * NARROW For devices such as smart phones (landscape or portrait) 
	 */
	NARROW,
	/**
	 * WIDE For devices such as tablets, lap tops, desktop computers (landscape or portrait)
	 */
	WIDE;

	public static DisplaySize forWidthInInches(int widthInInches) {
		if (widthInInches < 7) {
			return NARROW;
		} else {
			return WIDE;
		}
	}
}
