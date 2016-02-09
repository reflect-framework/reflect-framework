package nth.introspect.ui.style;

import java.awt.Color;

public class MaterialContentColors extends MaterialColors {
	private static final Color GRAY_50 = ColorUtil.hex2Color("FAFAFA");
	private static final Color GRAY_950 = ColorUtil.hex2Color("0F0F0F");
	
	public final static MaterialContentColors LIGHT=new MaterialContentColors(Color.white);
	public final static MaterialContentColors DARK=new MaterialContentColors(GRAY_950);
	
	private MaterialContentColors(Color backgroundColor) {
		super(backgroundColor);
	}

	public static boolean isDark(Color color) {
		return ColorUtil.isDark(color);
	}
}
