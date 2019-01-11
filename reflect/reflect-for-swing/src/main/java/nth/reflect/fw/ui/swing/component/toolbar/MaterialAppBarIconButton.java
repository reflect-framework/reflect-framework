package nth.reflect.fw.ui.swing.component.toolbar;

import java.net.MalformedURLException;

import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.style.ReflectColors;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;

public class MaterialAppBarIconButton extends MaterialButton {

	private static final long serialVersionUID = 5925859067046013332L;

	public MaterialAppBarIconButton(ReflectColors reflectColors, String fontIconUrl) throws MalformedURLException {
		super(ApplicationBarStyle.getForeground1(reflectColors) ,
				ApplicationBarStyle.getBackgroundHighLighted(reflectColors),
				ApplicationBarStyle.getIconPadding(),
				ApplicationBarStyle.getIconSize(), fontIconUrl);
	}

}
