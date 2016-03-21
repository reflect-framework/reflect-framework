package nth.introspect.ui.style;

public class DisplayType {

	private final DisplaySize size;
	private final DisplayScale scale;

	public DisplayType(int widthInInches, boolean hasKeyboardOrMouse) {
		size = DisplaySize.forWidthInInches(widthInInches);
		scale = DisplayScale.forHasKeyboardOrMouse(hasKeyboardOrMouse);
	}

	public DisplaySize getSize() {
		return size;
	}

	public DisplayScale getScale() {
		return scale;
	}
	
}
