package nth.reflect.fw.ui.style;

/**
 * @author nilsth
 *
 */

public enum ReflectDisplayWidth {

	/**
	 * {@link ReflectDisplayWidth#SMALL} is when the display is smaller than 10
	 * centimeter. This often corresponds with a
	 * <a href="https://en.wikipedia.org/wiki/Smartphone">smart phone</a> in a
	 * <a href="https://en.wikipedia.org/wiki/Page_orientation">portrait or
	 * landscape orientation</a>.
	 */
	SMALL(10),
	/**
	 * {@link ReflectDisplayWidth#MEDIUM} is when the display is bigger than
	 * {@link ReflectDisplayWidth#SMALL} and is smaller than 20 centimeter. This
	 * often corresponds with
	 * <a href="https://en.wikipedia.org/wiki/Tablet_computer">tablet </a> in a
	 * <a href="https://en.wikipedia.org/wiki/Page_orientation">portrait or
	 * landscape orientation</a>.
	 */
	MEDIUM(20),
	/**
	 * {@link ReflectDisplayWidth#LARGE} is when the display is bigger than
	 * {@link ReflectDisplayWidth#MEDIUM}. This often corresponds with
	 * <a href="https://en.wikipedia.org/wiki/Computer_monitor">desktop
	 * monitor</a> in a
	 * <a href="https://en.wikipedia.org/wiki/Page_orientation">portrait or
	 * landscape orientation</a>.
	 */
	LARGE(Integer.MAX_VALUE);

	private final int maxSizeInCentimeters;

	ReflectDisplayWidth(int maxSizeInCentimeters) {
		this.maxSizeInCentimeters = maxSizeInCentimeters;
	}

	public int getMaxSizeInCentimeters() {
		return maxSizeInCentimeters;
	}

	public int getMaxSizeInPixels() {
		return (int) (maxSizeInCentimeters * 37.7952755906);
	}

	public static ReflectDisplayWidth forCentimeters(int widthInCentimeters) {
		for (ReflectDisplayWidth reflectDisplayWidth : values()) {
			if (widthInCentimeters < reflectDisplayWidth.getMaxSizeInCentimeters()) {
				return reflectDisplayWidth;
			}
		}
		return ReflectDisplayWidth.LARGE;
	}

	public static ReflectDisplayWidth forPixels(int widthInPixels) {
		for (ReflectDisplayWidth reflectDisplayWidth : values()) {
			if (widthInPixels < reflectDisplayWidth.getMaxSizeInPixels()) {
				return reflectDisplayWidth;
			}
		}
		return ReflectDisplayWidth.LARGE;
	}

}
