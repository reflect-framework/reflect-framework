package nth.introspect.swing.component.toolbar;

import javax.swing.JLabel;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.swing.util.AwtFontFactory;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialStyle;

public class MaterialAppBarTitle extends JLabel {

	private static final long serialVersionUID = -4059858335846217368L;

	public MaterialAppBarTitle(UserInterfaceContainer userInterfaceContainer) {
		 IntrospectApplication application = userInterfaceContainer.get(IntrospectApplication.class);
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
		String title=applicationInfo.getDisplayName();
		setText(title);
		GraphicalUserinterfaceController<?> controller=userInterfaceContainer.get(GraphicalUserinterfaceController.class);
		MaterialStyle materialStyle = controller.getMaterialStyle();
		setFont(AwtFontFactory.create(materialStyle.getApplicationToolbarTitleStyle().getFont()));
	}

}
