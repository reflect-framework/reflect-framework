package nth.reflect.ui.vaadin10.css;

public enum Cursor {
	/**
	 *Default. The browser sets a cursor
	 */
	BLOCK,
	/**
	 * The cursor is a pointer and indicates a link
	 */
	POINTER;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
