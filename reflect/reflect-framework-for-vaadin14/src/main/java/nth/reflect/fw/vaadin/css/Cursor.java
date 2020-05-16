package nth.reflect.fw.vaadin.css;

public enum Cursor {

	/** The cursor indicates that something can be scrolled in any direction */
	ALL_SCROLL,
	/** Default. The browser sets a cursor */
	AUTO,
	/** The cursor indicates that a cell (or set of cells) may be selected */
	CELL,
	/** The cursor indicates that a context-menu is available */
	CONTEXT_MENU,
	/** The cursor indicates that the column can be resized horizontally */
	COL_RESIZE,
	/** The cursor indicates something is to be copied */
	COPY,
	/** The cursor render as a crosshair */
	CROSSHAIR,
	/** The default cursor */
	DEFAULT,
	/**
	 * The cursor indicates that an edge of a box is to be moved right (east)
	 */
	E_RESIZE,
	/** Indicates a bidirectional resize cursor */
	EW_RESIZE,
	/** The cursor indicates that something can be grabbed */
	GRAB,
	/** The cursor indicates that something can be grabbed */
	GRABBING,
	/** The cursor indicates that help is available */
	HELP,
	/** The cursor indicates something is to be moved */
	MOVE,
	/** The cursor indicates that an edge of a box is to be moved up (north) */
	N_RESIZE,
	/**
	 * The cursor indicates that an edge of a box is to be moved up and right
	 * (north/east)
	 */
	NE_RESIZE,
	/** Indicates a bidirectional resize cursor */
	NESW_RESIZE,
	/** Indicates a bidirectional resize cursor */
	NS_RESIZE,
	/**
	 * The cursor indicates that an edge of a box is to be moved up and left
	 * (north/west)
	 */
	NW_RESIZE,
	/** Indicates a bidirectional resize cursor */
	NWSE_RESIZE,
	/** The cursor indicates that the dragged item cannot be dropped here */
	NO_DROP,
	/** No cursor is rendered for the element */
	NONE,
	/** The cursor indicates that the requested action will not be executed */
	NOT_ALLOWED,
	/** The cursor is a pointer and indicates a link */
	POINTER,
	/** The cursor indicates that the program is busy (in progress) */
	PROGRESS,
	/** The cursor indicates that the row can be resized vertically */
	ROW_RESIZE,
	/**
	 * The cursor indicates that an edge of a box is to be moved down (south)
	 */
	S_RESIZE,
	/**
	 * The cursor indicates that an edge of a box is to be moved down and right
	 * (south/east)
	 */
	SE_RESIZE,
	/**
	 * The cursor indicates that an edge of a box is to be moved down and left
	 * (south/west)
	 */
	SW_RESIZE,
	/** The cursor indicates text that may be selected */
	TEXT,
	/**
	 * A comma separated list of URLs to custom cursors. Note: Always specify a
	 * generic cursor at the end of the list, in case none of the URL-defined
	 * cursors can be used
	 */
	URL,
	/** The cursor indicates vertical-text that may be selected */
	VERTICAL_TEXT,
	/** The cursor indicates that an edge of a box is to be moved left (west) */
	W_RESIZE,
	/** The cursor indicates that the program is busy */
	WAIT,
	/** The cursor indicates that something can be zoomed in */
	ZOOM_IN,
	/** The cursor indicates that something can be zoomed out */
	ZOOM_OUT,
	/** Sets this property to its default value. Read about initial */
	INITIAL,
	/** Inherits this property from its parent element. Read about inherit */
	INHERIT;

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", "-");
	}
}
