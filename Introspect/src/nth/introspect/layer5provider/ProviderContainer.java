package nth.introspect.layer5provider;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

/**
 * This {@link DependencyInjectionContainer} represents the {@link ProviderLayer}
 * @author nilsth
 *
 */
public class ProviderContainer extends DependencyInjectionContainer {

	public ProviderContainer(IntrospectApplication application) {
		super();
		
		add(application);
		
		// add provider classes
		add(application.getPathProviderClass());
		add(application.getLanguageProviderClass());
		add(application.getValidationProviderClass());
		add(application.getAuthorizationProviderClass());
		add(application.getNotificationProviderClass());
		add(application.getReflectionProviderClass());
		add(application.getVersionProviderClass());
	}

	

}
