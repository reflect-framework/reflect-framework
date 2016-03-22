package nth.introspect.ui.style;

public enum DisplayScale {
	/**
	 * When the mouse and keyboard are the primary input methods, measurements may be condensed to accommodate denser layouts.
	 */
	DENSE(0.85),
	/**
	 * When a touch screen is the primary input methods, measurements may NOT be condensed to accommodate easy to control input.
	 */
	NONE_DENSE(1)
	;

	private double scale;

	private DisplayScale(double scale) {
		this.scale = scale;
	}
	
	public int scale(int size) {
		return (int) (size* scale);
	}
	
	public static DisplayScale forHasKeyboardOrMouse(boolean hasKeyboardOrMouse) {
		if (hasKeyboardOrMouse) {
			return DENSE;
		} else {
			return NONE_DENSE;
		}
	}

}
