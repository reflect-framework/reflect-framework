package nth.introspect.ui.style;

import nth.introspect.ui.style.basic.Color;

/**
 * {@link MaterialColors} hold the color sets to be used in an application.
 * @author nilsth
 *
 *
 *try to deprecate this class by getting the colors from CSS as RfxItemTreeCell
 */
@Deprecated
public class MaterialColors  {

	private  static MaterialColorSet primaryColorSet;
	private  static MaterialColorSet secondaryColorSet;
	private  static MaterialColorSet accentColorSet;
	private  static MaterialColorSet contentColorSet;

	
	public static void init(Color primaryColor, Color secondaryColor, Color accentColor, ContentColor contentColor) {
		primaryColorSet=new MaterialColorSet(primaryColor);
		secondaryColorSet=new MaterialColorSet(secondaryColor);
		accentColorSet=new MaterialColorSet(accentColor);
		contentColorSet=new MaterialColorSet(contentColor.getColor());
	}
	


	public static MaterialColorSet getPrimaryColorSet() {
		return primaryColorSet;
	}

	
	public static MaterialColorSet getSecondaryColorSet() {
		return secondaryColorSet;
	}


	public static MaterialColorSet getAccentColorSet() {
		return accentColorSet;
	}

	public static MaterialColorSet getContentColorSet() {
		return contentColorSet;
	}


	

}
