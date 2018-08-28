package nth.reflect.ui.vaadin10.css;

public enum Overflow {
	/**
	 * Default. The overflow is not clipped. It renders outside the element's
	 * box
	 */
	VISIBLE,
	/**
	 * The overflow is clipped, and the rest of the content will be invisible
	 */
	HIDDEN,
	/**
	 * The overflow is clipped, but a scroll bar is added to see the rest of the
	 * content
	 */
	SCROLL,
	/**
	 * If overflow is clipped, a scrollbar should be added to see the rest of
	 * the content
	 */
	AUTO;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
