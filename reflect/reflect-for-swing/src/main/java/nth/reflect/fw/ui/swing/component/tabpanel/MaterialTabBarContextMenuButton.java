package nth.reflect.fw.ui.swing.component.tabpanel;

import java.net.MalformedURLException;

import nth.reflect.fw.ui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;

public class MaterialTabBarContextMenuButton extends MaterialButton {

	private static final long serialVersionUID = -1862078530902019839L;

	public MaterialTabBarContextMenuButton(ReflectColors reflectColors) throws MalformedURLException {
		super(ApplicationBarStyle.getForeground1(reflectColors), ApplicationBarStyle.getBackgroundHighLighted(reflectColors), ApplicationBarStyle.getIconPadding(), ApplicationBarStyle.getIconSize(), FontAwesomeUrl.ELLIPSIS_V);
	}

}
