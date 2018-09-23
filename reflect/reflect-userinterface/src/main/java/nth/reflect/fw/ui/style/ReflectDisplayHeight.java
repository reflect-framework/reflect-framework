package nth.reflect.fw.ui.style;

/**
 * @author nilsth
 *
 */

public enum ReflectDisplayHeight {

	
	/**
	 * {@link ReflectDisplayHeight#SMALL} is when the display is smaller than 10
	 * centimeter. This often corresponds with a
	 * <a href="https://en.wikipedia.org/wiki/Smartphone">smart phones</a>
	 * in a <a href="https://en.wikipedia.org/wiki/Page_orientation">portrait or
	 * landscape orientation</a>.
	 */
	SMALL(10),
	/**
	 * {@link ReflectDisplayHeight#MEDIUM} is when the display is bigger than
	 * {@link ReflectDisplayHeight#SMALL} and is smaller than 20 centimeter. This
	 * often corresponds with
	 * <a href="https://en.wikipedia.org/wiki/Tablet_computer">tablet
	 * computer</a> in a
	 * <a href="https://en.wikipedia.org/wiki/Page_orientation">portrait or
	 * landscape orientation</a>.
	 */
	MEDIUM(20),
	/**
	 * {@link ReflectDisplayHeight#LARGE} is when the display is bigger than
	 * {@link ReflectDisplayHeight#MEDIUM}. This
	 * often corresponds with
	 * <a href="https://en.wikipedia.org/wiki/Computer_monitor">computer monitor</a> in a
	 * <a href="https://en.wikipedia.org/wiki/Page_orientation">portrait or
	 * landscape orientation</a>.
	 */
	LARGE(Integer.MAX_VALUE);

	private final int maxSizeInCentimeters;

	ReflectDisplayHeight(int maxSizeInCentimeters) {
		this.maxSizeInCentimeters = maxSizeInCentimeters;
	}

	public int getMaxSizeInCentimeters() {
		return maxSizeInCentimeters;
	}

	public int getMaxSizeInPixels() {
		return (int) (maxSizeInCentimeters*37.7952755906);
	}
	
	public static ReflectDisplayHeight forCentimeters(int heightInCentimeters) {
		for (ReflectDisplayHeight reflectDisplayHeight : values()) {
			if (heightInCentimeters<reflectDisplayHeight.getMaxSizeInCentimeters() ) {
				return reflectDisplayHeight;
			}
		}
		return ReflectDisplayHeight.LARGE;
	}

	public static ReflectDisplayHeight forPixels(int heightInPixels) {
		for (ReflectDisplayHeight reflectDisplayHeight : values()) {
			if (heightInPixels < reflectDisplayHeight.getMaxSizeInPixels()) {
				return reflectDisplayHeight;
			}
		}
		return ReflectDisplayHeight.LARGE;
	}

}
