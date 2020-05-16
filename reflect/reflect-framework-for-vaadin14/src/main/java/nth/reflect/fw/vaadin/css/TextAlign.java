package nth.reflect.fw.vaadin.css;

public enum TextAlign {
	/** Aligns the text to the left */
	LEFT,
	/** Aligns the text to the right */
	RIGHT,
	/** Centers the text */
	CENTER,
	/**
	 * Stretches the lines so that each line has equal width (like in newspapers
	 * and magazines)
	 */
	JUSTIFY,
	/** Sets this property to its default value. Read about initial */
	INITIAL,
	/** Inherits this property from its parent element. Read about inherit */
	INHERIT;
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
