package nth.reflect.fw.vaadin.css;

public enum Overflow {
	/**
	 * The overflow is not clipped. It renders outside the element's box. This is default
	 */
	VISIBLE,
	/**
	 * The overflow is clipped, and the rest of the content will be invisible	
	 */
	HIDDEN,
	/**
	 * The overflow is clipped, but a scroll-bar is added to see the rest of the content
	 */
	SCROLL,
	/**
	 * If overflow is clipped, a scroll-bar should be added to see the rest of the content
	 */
	AUTO,
	/**
	 * 	Sets this property to its default value. 
	 */
	INITIAL,
	/**
	 * Inherits this property from its parent element. 
	 */
	INHERIT;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
