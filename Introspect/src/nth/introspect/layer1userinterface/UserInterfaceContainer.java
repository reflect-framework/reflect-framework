package nth.introspect.layer1userinterface;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

/**
 * This {@link DependencyInjectionContainer} represents the {@link UserInterfaceLayer}
 * 
 * @author nilsth
 * 
 */
public class UserInterfaceContainer extends DependencyInjectionContainer {

//	private LanguageProvider languageProvider;
//	private final IntrospectApplication application;
//	private UserInterfaceController<?> userInterfaceController;
//	private ReflectionProvider reflectionProvider;
//	private PathProvider pathProvider;
//	private IntrospectApplication introspectApplication;
//	private NotificationProvider notificationProvider;
//	private ClassInfo introspectApplicationClassInfo;
//	private AboutProvider aboutProvider;

	public UserInterfaceContainer(IntrospectApplication application) {
		super(new ServiceContainer(application));
		add(application.getUserInterfaceControllerClass());
	}


	@Override
	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = super.getAllClasses();
		allClasses.remove(DependencyInjectionContainer.class);
		allClasses.add(UserInterfaceContainer.class);
		return allClasses;
	}	

}
