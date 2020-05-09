package nth.reflect.ui.vaadin.css;

public enum Position {
	/**
	 * Default value. Elements render in order, as they appear in the document
	 * flow
	 */
	STATIC,
	/**
	 * The element is positioned relative to its normal position, so "left:20px"
	 * adds 20 pixels to the element's LEFT position
	 */
	RELATIVE,
	/**
	 * The element is positioned relative to its first positioned (not static)
	 * ancestor element
	 */
	ABSOLUTE,
	/**
	 * The element is positioned relative to the browser window
	 */
	FIXED;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
