package nth.introspect.ui.style;

public class DisplayType {

	private final DisplaySize size;
	private final DisplayDense dense;

	public DisplayType(int widthInInches, boolean hasKeyboardAndMouse) {
		size = DisplaySize.forWidthInInches(widthInInches);
		dense = DisplayDense.forHasKeyboardAndMouse(hasKeyboardAndMouse);
	}

	public DisplaySize getSize() {
		return size;
	}

	public DisplayDense getDense() {
		return dense;
	}
	
	public boolean isDense() {
		return dense==DisplayDense.DENSE;
	}
}
