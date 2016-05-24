package nth.introspect.layer5provider;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.container.exception.ProviderNotDefined;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.url.ReflectUrlStreamHandlerFactory;
import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * This {@link DependencyInjectionContainer} represents the
 * {@link ProviderLayer}
 * 
 * @author nilsth
 *
 */
public class ProviderContainer extends DependencyInjectionContainer {

	public ProviderContainer(IntrospectApplication application) {
		super();

		add(application);

		// add provider classes
		for (Class<? extends UrlProvider> urlProviderClass : application.getUrlProviderClasses()) {
			add(urlProviderClass);
		}

		try {
			ReflectUrlStreamHandlerFactory urlStreamHandlerFactory = new ReflectUrlStreamHandlerFactory(
					application, this);
			urlStreamHandlerFactory.register();
		} catch (Error error) {
			// assuming we already set the urlStreamHandlerFactory
		}

		add(application.getLanguageProviderClass(), LanguageProvider.class, application);
		add(application.getValidationProviderClass(), ValidationProvider.class, application);
		add(application.getAuthorizationProviderClass(), AuthorizationProvider.class, application);
		add(application.getNotificationProviderClass(), NotificationProvider.class, application);
		add(application.getReflectionProviderClass(), ReflectionProvider.class, application);
		add(application.getAboutProviderClass(), AboutProvider.class, application);
	}

	private void add(Class<? extends Provider> provider, Class<?> providerType,
			IntrospectApplication application) {
		if (provider == null || !providerType.isAssignableFrom(provider)) {
			throw new ProviderNotDefined(application, providerType);
		}
		add(provider);
	}

}
