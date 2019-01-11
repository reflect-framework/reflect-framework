package nth.reflect.fw.gui.style.basic;

/**
 * This class was created to get away from AWT or JavaFX dependencies
 * 
 * @author nilsth
 *
 */
public class Color {

	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color RED = new Color (255,0,0);
	private final int red;
	private final int green;
	private final int blue;
	private final int alpha;

	public Color(String hex) {
		this(getRed(hex), getGreen(hex), getBlue(hex));
	}

	private static int getBlue(String hex) {
		hex = hex.replace("#", "");
		int blue = Integer.valueOf(hex.substring(4, 6), 16);
		return blue;
	}

	private static int getGreen(String hex) {
		hex = hex.replace("#", "");
		int green = Integer.valueOf(hex.substring(2, 4), 16);
		return green;
	}

	private static int getRed(String hex) {
		hex = hex.replace("#", "");
		int red = Integer.valueOf(hex.substring(0, 2), 16);
		return red;
	}

	public Color(int red, int green, int blue) {
		this(red, green, blue, 255);
	}

	public Color(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public Color(int red, int green, int blue, double alpha) {
		this(red, green, blue, (int) (alpha * 255));
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public int getAlpha() {
		return alpha;
	}

	public Color deriveAlpha(double alpha) {
		return new Color(red, green, blue, alpha);
	}

	private int getBrightness() {
		return (int) Math.sqrt(red * red * .241 + green * green * .691 + blue * blue * .068);
	}

	public boolean isDark() {
		int brightness = getBrightness();
		boolean dark = brightness < 250;
		return dark;
	}

	/**
	 * Make a color darker.
	 * 
	 * @param color
	 *            Color to make darker.
	 * @param fraction:
	 *            0.8= 20% darker, 1=same, 1.2=20% lighter Darkness fraction.
	 * @return Darker color.
	 */
	public Color deriveDarknes(double fraction) {
		int red = (int) Math.round(this.red * fraction);
		int green = (int) Math.round(this.green * fraction);
		int blue = (int) Math.round(this.blue * fraction);

		if (red < 0)
			red = 0;
		else if (red > 255)
			red = 255;
		if (green < 0)
			green = 0;
		else if (green > 255)
			green = 255;
		if (blue < 0)
			blue = 0;
		else if (blue > 255)
			blue = 255;

		return new Color(red, green, blue, alpha);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Color))
			return false;
		Color color = (Color) obj;
		return red == color.getRed() && green == color.getGreen() && blue == color.getBlue()
				&& alpha == color.getAlpha();
	}
}