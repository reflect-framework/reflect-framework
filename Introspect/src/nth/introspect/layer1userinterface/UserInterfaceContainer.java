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

	private LanguageProvider languageProvider;
	private final IntrospectApplication application;
	private UserInterfaceController<?> userInterfaceController;
	private ReflectionProvider reflectionProvider;
	private PathProvider pathProvider;
	private IntrospectApplication introspectApplication;
	private NotificationProvider notificationProvider;
	private ClassInfo introspectApplicationClassInfo;
	private AboutProvider aboutProvider;

	public UserInterfaceContainer(IntrospectApplication application) {
		super(new ServiceContainer(application));
		this.application = application;
		add(application.getUserInterfaceControllerClass());
	}

	public List<Object> getServiceObjects() {
		List<Class<?>> serviceClasses = getApplication().getServiceClasses();
		List<Object> serviceObjects = new ArrayList<Object>();
		for (Class<?> serviceClass : serviceClasses) {
			Object serviceObject = get(serviceClass);
			serviceObjects.add(serviceObject);
		}
		return serviceObjects;
	}

	@Override
	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = super.getAllClasses();
		allClasses.remove(DependencyInjectionContainer.class);
		allClasses.add(UserInterfaceContainer.class);
		return allClasses;
	}

	/**
	 * Convenient method to get the {@link UserInterfaceController} FIXME:
	 * remove this method (user constructor injection for registered objects in
	 * container instead)
	 * 
	 * @return {@link UserInterfaceController}
	 */
	public UserInterfaceController<?> getUserInterfaceController() {
		if (userInterfaceController == null) {
			userInterfaceController = (UserInterfaceController<?>) get(UserInterfaceController.class);
		}
		return userInterfaceController;
	}

	/**
	 * Convenient method to get the {@link ReflectionProvider} FIXME: remove
	 * this method (user constructor injection for registered objects in
	 * container instead)
	 * 
	 * @return {@link ReflectionProvider}
	 */
	public ReflectionProvider getReflectionProvider() {
		if (reflectionProvider == null) {
			reflectionProvider = (ReflectionProvider) get(ReflectionProvider.class);
		}
		return reflectionProvider;
	}

	/**
	 * Convenient method to get the {@link LanguageProvider} FIXME: remove this
	 * method (user constructor injection for registered objects in container
	 * instead)
	 * 
	 * @return {@link LanguageProvider}
	 */
	public LanguageProvider getLanguageProvider() {
		if (languageProvider == null) {
			languageProvider = (LanguageProvider) get(LanguageProvider.class);
		}
		return languageProvider;
	}

	public PathProvider getPathProvider() {
		if (pathProvider == null) {
			pathProvider = (PathProvider) get(PathProvider.class);
		}
		return pathProvider;
	}

	public IntrospectApplication getIntrospectApplication() {
		if (introspectApplication == null) {
			introspectApplication = (IntrospectApplication) get(IntrospectApplication.class);
		}
		return introspectApplication;
	}

	public ClassInfo getIntrospectApplicationClassInfo() {
		if (introspectApplicationClassInfo==null)	{
			IntrospectApplication application=getIntrospectApplication();
			ReflectionProvider reflectionProvider=getReflectionProvider();
			introspectApplicationClassInfo=reflectionProvider.getClassInfo(application.getClass());
		}
		return introspectApplicationClassInfo;
	}
	

	public NotificationProvider getNotificationProvider() {
		if (notificationProvider==null) {
			notificationProvider=(NotificationProvider) get(NotificationProvider.class);
		}
		return notificationProvider;
	}

	public AboutProvider getAboutProvider() {
		if (aboutProvider==null) {
			aboutProvider=(AboutProvider) get(AboutProvider.class);
		}
		return aboutProvider;
	}

	public IntrospectApplication getApplication() {
		return application;
	}
	
	

}
