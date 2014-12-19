package nth.introspect.container.impl;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.definition.UserInterfaceLayer;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * See {@link UserInterfaceLayer}
 * @author nilsth
 *
 */
public class UserInterfaceContainer extends IntrospectContainer {

	private LanguageProvider languageProvider;
	private AuthorizationProvider authorizationProvider;
	private PathProvider pathProvider;
	private final IntrospectApplication application;
	private UserInterfaceProvider<?> userInterfaceProvider;
	private DomainInfoProvider domainInfoProvider;
	private AboutProvider aboutProvider;
	private ValidationProvider validationProvider;

	public UserInterfaceContainer(IntrospectApplication application) {
		super( new ServiceContainer(application));
		this.application = application;
		add(application.getUserInterfaceProviderClass());
	}

	public List<Object> getServiceObjects() {
		List<Class<?>> serviceClasses = application.getServiceClasses();
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
		allClasses.remove(IntrospectContainer.class);
		allClasses.add(UserInterfaceContainer.class);
		return allClasses;
	}

	/**
	 * Convenient method to get the {@link UserInterfaceProvider}
	 * 
	 * @return {@link UserInterfaceProvider}
	 */
	public UserInterfaceProvider<?> getUserInterfaceProvider() {
		if (userInterfaceProvider == null) {
			userInterfaceProvider = (UserInterfaceProvider<?>) get(UserInterfaceProvider.class);
		}
		return userInterfaceProvider;
	}

	/**
	 * Convenient method to get the {@link PathProvider}
	 * 
	 * @return {@link PathProvider}
	 */
	public PathProvider getPathProvider() {
		if (pathProvider == null) {
			pathProvider = (PathProvider) get(PathProvider.class);
		}
		return pathProvider;
	}

	/**
	 * Convenient method to get the {@link AuthorizationProvider}
	 * 
	 * @return {@link AuthorizationProvider}
	 */
	public AuthorizationProvider getAuthorizationProvider() {
		if (authorizationProvider == null) {
			authorizationProvider = (AuthorizationProvider) get(AuthorizationProvider.class);
		}
		return authorizationProvider;
	}

	/**
	 * Convenient method to get the {@link DomainInfoProvider}
	 * 
	 * @return {@link DomainInfoProvider}
	 */
	public DomainInfoProvider getDomainInfoProvider() {
		if (domainInfoProvider == null) {
			domainInfoProvider = (DomainInfoProvider) get(DomainInfoProvider.class);
		}
		return domainInfoProvider;
	}

	/**
	 * Convenient method to get the {@link LanguageProvider}
	 * 
	 * @return {@link LanguageProvider}
	 */
	public LanguageProvider getLanguageProvider() {
		if (languageProvider == null) {
			languageProvider = (LanguageProvider) get(LanguageProvider.class);
		}
		return languageProvider;
	}

	/**
	 * Convenient method to get the {@link AboutProvider}
	 * 
	 * @return {@link AboutProvider}
	 */
	public AboutProvider getAboutProvider() {
		if (aboutProvider == null) {
			aboutProvider = (AboutProvider) get(AboutProvider.class);
		}
		return aboutProvider;
	}

	/**
	 * Convenient method to get the {@link ValidationProvider}
	 * 
	 * @return {@link ValidationProvider}
	 */
	public ValidationProvider getValidationProvider() {
		if (validationProvider == null) {
			validationProvider = (ValidationProvider) get(ValidationProvider.class);
		}
		return validationProvider;
	}

}
