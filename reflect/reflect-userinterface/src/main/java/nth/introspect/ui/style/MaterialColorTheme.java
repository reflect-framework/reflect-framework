package nth.introspect.ui.style;

import nth.introspect.layer1userinterface.UserInterfaceContainer;

public class MaterialColorTheme {

	
	public final static MaterialColorTheme defaultTheme=new MaterialColorTheme(MaterialColorPalette.TEAL, MaterialColorPalette.DEEP_ORANGE, MaterialContentColors.LIGHT);
	
	private final MaterialColors primaryDark;
	private final MaterialColors primaryMedium;
	private final MaterialColors primaryLight;
	private final MaterialColors accentColor;
	private final MaterialContentColors backGroundColor;

	
	public MaterialColorTheme(UserInterfaceContainer userInterfaceContainer) {
		this(MaterialColorPalette.BLUE, MaterialColorPalette.DEEP_ORANGE, MaterialContentColors.LIGHT);
	}
	
	public MaterialColorTheme(MaterialColorPalette primaryColors, MaterialColorPalette accentColor, MaterialContentColors backgroundColor) {
		this.primaryDark=primaryColors.getColor500();
		this.primaryMedium=primaryColors.getColor300();
		this.primaryLight=primaryColors.getColor100();
		this.accentColor=accentColor.getColor500();
		this.backGroundColor=backgroundColor;
	}

	public MaterialColors getPrimaryDark() {
		return primaryDark;
	}

	public MaterialColors getPrimaryMedium() {
		return primaryMedium;
	}

	public MaterialColors getPrimaryLight() {
		return primaryLight;
	}

	public MaterialColors getAccent() {
		return accentColor;
	}

	public MaterialContentColors getBackGround() {
		return backGroundColor;
	}
	
	
}
