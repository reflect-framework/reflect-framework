package nth.reflect.fw.ui.swing.component.toolbar;

import javax.swing.JLabel;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.swing.util.AwtFontFactory;

public class MaterialAppBarTitle extends JLabel {

	private static final long serialVersionUID = -4059858335846217368L;

	public MaterialAppBarTitle(UserInterfaceContainer userInterfaceContainer) {
		ReflectApplication application = userInterfaceContainer.get(ReflectApplication.class);
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
		String title=applicationInfo.getDisplayName();
		setText(title);
		GraphicalUserinterfaceController<?> controller=userInterfaceContainer.get(GraphicalUserinterfaceController.class);
		MaterialStyle materialStyle = controller.getMaterialStyle();
		setFont(AwtFontFactory.create(materialStyle.getApplicationToolbarTitleStyle().getFont()));
	}

}
