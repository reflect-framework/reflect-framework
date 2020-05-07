package com.acme.web.shop;

import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ColorProvider;

public class WebShopColorProvider {

	private static ColorProvider colors;

	static {
		colors = new ColorProvider(MaterialColorPalette.orange700(), MaterialColorPalette.lightBlue500(),
				MaterialColorPalette.white());
	}

	public static ColorProvider get() {
		return colors;
	}

}
