package nth.reflect.fw.vaadin.css;

public enum FloatType {

	/** No floating elements allowed on the left side */
	LEFT,
	/** No floating elements allowed on the right side */
	RIGHT,
	/** No floating elements allowed on either the left or the right side */
	BOTH,
	/** The element inherits the clear value of its parent */
	INHERIT;
	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
