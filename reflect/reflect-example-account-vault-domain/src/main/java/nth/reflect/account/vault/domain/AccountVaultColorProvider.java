package nth.reflect.account.vault.domain;

import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ColorProvider;

public class AccountVaultColorProvider {

	private static ColorProvider colors = new ColorProvider(MaterialColorPalette.teal700(),
			MaterialColorPalette.orange500(), MaterialColorPalette.white());

	public static ColorProvider get() {
		return colors;
	}
}
