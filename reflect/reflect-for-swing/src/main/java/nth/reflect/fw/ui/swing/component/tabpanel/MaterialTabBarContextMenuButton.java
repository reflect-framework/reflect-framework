package nth.reflect.fw.ui.swing.component.tabpanel;

import java.net.MalformedURLException;

import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;

public class MaterialTabBarContextMenuButton extends MaterialButton {

	private static final long serialVersionUID = -1862078530902019839L;

	public MaterialTabBarContextMenuButton(ColorProvider colorProvider) throws MalformedURLException {
		super(ApplicationBarStyle.getForeground1(colorProvider), ApplicationBarStyle.getBackgroundHighLighted(colorProvider), ApplicationBarStyle.getIconPadding(), ApplicationBarStyle.getIconSize(), FontAwesomeUrl.ELLIPSIS_V);
	}

}
