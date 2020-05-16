package nth.reflect.fw.vaadin.mainwindow;

import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;

public class ApplicationTitle extends Div {

	private static final long serialVersionUID = -4769993079364950736L;

	public ApplicationTitle(UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationClassInfo applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		String title = applicationClassInfo.getDisplayName().getTranslation();
		setText(title);
	}
}
