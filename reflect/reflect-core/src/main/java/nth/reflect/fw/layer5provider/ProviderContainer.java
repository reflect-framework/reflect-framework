package nth.reflect.fw.layer5provider;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.container.exception.ProviderNotDefined;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandlerFactory;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

/**
 * This {@link DependencyInjectionContainer} represents the
 * {@link ProviderLayer}
 * 
 * @author nilsth
 *
 */
public class ProviderContainer extends DependencyInjectionContainer {

	public ProviderContainer(ReflectApplication application) {
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
			ReflectApplication application) {
		if (provider == null || !providerType.isAssignableFrom(provider)) {
			throw new ProviderNotDefined(application, providerType);
		}
		add(provider);
	}

}
