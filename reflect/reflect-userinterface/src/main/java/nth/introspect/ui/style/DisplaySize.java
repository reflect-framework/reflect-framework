package nth.introspect.ui.style;

public enum DisplaySize {

	/**
	 * SMALL_PORTRAIT For devices such as smart phones in portrait
	 */
	SMALL_PORTRAIT,

	/**
	 * SMALL_LANDSCAPE For devices such as smart phones in landscape
	 */
	SMALL_LANDSCAPE,

	/**
	 * LARGE_PORTRAIT For devices such as tablets, lap tops, desktop computers
	 * in portrait
	 */
	LARGE_PORTRAIT,

	/**
	 * LARGE_LANDSCAPE For devices such as tablets, lap tops, desktop computers
	 * in landscape
	 */
	LARGE_LANDSCAPE;

	public static DisplaySize forPixels(int widtInPixels, int heightInPixels) {
		boolean isPortrait=widtInPixels<heightInPixels;
		boolean isSmall=widtInPixels>700;
		
			if (isSmall) {
				if(isPortrait) {
					return SMALL_PORTRAIT;
				} else {
					return SMALL_LANDSCAPE;
				}
			} else {
				if(isPortrait) {
					return LARGE_PORTRAIT;
				} else {
					return SMALL_LANDSCAPE;
				}
			}
			
	}
}
