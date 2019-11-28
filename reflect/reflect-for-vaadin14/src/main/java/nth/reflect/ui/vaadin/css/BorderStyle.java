package nth.reflect.ui.vaadin.css;

public enum BorderStyle {

	/**
	 * The same as "none", except in border conflict resolution for table
	 * elements
	 */
	HIDDEN,
	/** Specifies a dotted border */
	DOTTED,
	/** Specifies a dashed border */
	DASHED,
	/** Specifies a solid border */
	SOLID,
	/** Specifies a double border */
	DOUBLE,
	/**
	 * Specifies a 3D grooved border. The effect depends on the border-color
	 * value
	 */
	GROOVE,
	/**
	 * Specifies a 3D ridged border. The effect depends on the border-color
	 * value
	 */
	RIDGE,
	/**
	 * Specifies a 3D inset border. The effect depends on the border-color value
	 */
	INSET,
	/**
	 * Specifies a 3D outset border. The effect depends on the border-color
	 * value
	 */
	OUTSET;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
