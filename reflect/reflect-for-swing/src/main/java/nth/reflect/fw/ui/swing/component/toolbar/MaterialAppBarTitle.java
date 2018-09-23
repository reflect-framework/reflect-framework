package nth.reflect.fw.ui.swing.component.toolbar;

import javax.swing.JLabel;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.component.ApplicationBarStyle;
import nth.reflect.fw.ui.swing.util.AwtFontFactory;
import nth.reflect.fw.ui.swing.util.ColorFactory;

public class MaterialAppBarTitle extends JLabel {

	private static final long serialVersionUID = -4059858335846217368L;

	public MaterialAppBarTitle(UserInterfaceContainer userInterfaceContainer) {
		ReflectApplication application = userInterfaceContainer.get(ReflectApplication.class);
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
		String title=applicationInfo.getDisplayName();
		setText(title);
		ReflectColors reflectColors = ReflectColors.getFrom(userInterfaceContainer);
		setForeground(ColorFactory.create(ApplicationBarStyle.getForeground1(reflectColors)));
		setFont(AwtFontFactory.create(ApplicationBarStyle.getTitleFont()));
	}

}
