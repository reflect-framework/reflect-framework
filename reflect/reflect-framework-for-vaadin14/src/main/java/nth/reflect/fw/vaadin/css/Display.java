package nth.reflect.fw.vaadin.css;

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
	NONE,
	/**
	 * Displays an element as a block-level flex container
	 */
	INLINE_BLOCK,
	/**
	 * Displays an element as an inline-level block container. The element
	 * itself is formatted as an inline element, but you can apply height and
	 * width allValues
	 */
	FLEX;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}
