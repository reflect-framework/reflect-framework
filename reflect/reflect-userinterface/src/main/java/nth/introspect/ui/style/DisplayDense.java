package nth.introspect.ui.style;

public enum DisplayDense {
	/**
	 * When the mouse and keyboard are the primary input methods, measurements may be condensed to accommodate denser layouts.
	 */
	DENSE,
	/**
	 * When a touch screen is the primary input methods, measurements may NOT be condensed to accommodate easy to control input.
	 */
	NONE_DENSE
	;

	public static DisplayDense forHasKeyboardAndMouse(boolean hasKeyboardAndMouse) {
		if (hasKeyboardAndMouse) {
			return DENSE;
		} else {
			return NONE_DENSE;
		}
	}

}
