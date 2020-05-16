package nth.reflect.fw.swing.component.toolbar;

import javax.swing.JLabel;

import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.fw.swing.util.AwtFontFactory;
import nth.reflect.fw.swing.util.ColorFactory;

public class MaterialAppBarTitle extends JLabel {

	private static final long serialVersionUID = -4059858335846217368L;

	public MaterialAppBarTitle(UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationClassInfo applicationInfo = reflectionProvider.getApplicationClassInfo();
		String title = applicationInfo.getDisplayName().getTranslation();
		setText(title);
		ColorProvider colorProvider = userInterfaceContainer.get(ColorProvider.class);
		setForeground(ColorFactory.create(ApplicationBarStyle.getForeground1(colorProvider)));
		setFont(AwtFontFactory.create(ApplicationBarStyle.getTitleFont()));
	}

}
