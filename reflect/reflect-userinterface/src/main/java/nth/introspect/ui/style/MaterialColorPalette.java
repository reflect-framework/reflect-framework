package nth.introspect.ui.style;


public enum MaterialColorPalette implements MaterialDesign {
	RED("F44336","E57373","FFCDD2"),
	PINK("E91E63","F06292","F8BBD0"),
	PURPLE("F8BBD0","BA68C8","E1BEE7"),
	DEEP_PURPLE("673AB7","9575CD","D1C4E9"),
	INDIGO("3F51B5","7986CB","C5CAE9"),
	BLUE("2196F3","64B5F6","BBDEFB"),
	LIGHT_BLUE("03A9F4","4FC3F7","B3E5FC"),
	CYAN("00BCD4","4DD0E1","B2EBF2"),
	TEAL("009688","4DB6AC","B2DFDB"),
	GREEN("4CAF50","81C784","C8E6C9"),
	LIGHT_GREEN("8BC34A","AED581","DCEDC8"),
	LIME("CDDC39","DCE775","F0F4C3"),
	YELLOW("FFEB3B","FFF176","FFF9C4"),
	AMBER("FFC107","FFD54F","FFECB3"),
	ORANGE("FF9800","FFB74D","FFE0B2"),
	DEEP_ORANGE("FF5722","FF8A65","FFCCBC"),
	BROWN("795548","A1887F","D7CCC8"),
	GREY("9E9E9E","E0E0E0","F5F5F5"), 
	BLUE_GRAY("607D8B","90A4AE","CFD8DC");

	private final MaterialColors color500;
	private final MaterialColors color300;
	private final MaterialColors color100;

	private  MaterialColorPalette(String color500hex, String color300hex, String color100hex) {
		color500 = new MaterialColors(color500hex);
		color300 = new MaterialColors(color300hex);
		color100 = new MaterialColors(color100hex);
	}
	
	public MaterialColors getColor500() {
		return color500;
	}
	
	public MaterialColors getColor300() {
		return color300;
	}

	public MaterialColors getColor100() {
		return color100;
	}

}
