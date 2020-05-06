package nth.reflect.all.feature;

import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ColorProvider;

public class AllFeatureColorProvider {

	private static ColorProvider colors = new ColorProvider(MaterialColorPalette.purple700(),
			MaterialColorPalette.yellow700(), MaterialColorPalette.white());

	public static ColorProvider get() {
		return colors;
	}

}
