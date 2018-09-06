package nth.reflect.ui.vaadin.css;

public enum Display {
	/**
	 * Displays an element as a block element (like
	 * <p>
	 * ). It starts on a new line, and takes up the whole width
	 */
	BLOCK,
	/**
	 * The element is completely removed
	 */
	NONE;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}
