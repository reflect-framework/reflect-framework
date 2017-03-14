package nth.introspect.ui.style;

public class DisplayType {

	private final DisplaySize size;
	private final DisplayScale scale;

	public DisplayType( int widtInPixels, int heightInPixels, boolean hasKeyboardOrMouse) {
		size = DisplaySize.forPixels(widtInPixels, heightInPixels);
		scale = DisplayScale.forHasKeyboardOrMouse(hasKeyboardOrMouse);
	}

	public DisplaySize getSize() {
		return size;
	}

	public DisplayScale getScale() {
		return scale;
	}
	
}
