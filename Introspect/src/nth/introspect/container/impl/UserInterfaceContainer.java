package nth.introspect.container.impl;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.definition.UserInterfaceLayer;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;

/**
 * This {@link IntrospectContainer} represents the {@link UserInterfaceLayer}
 * 
 * @author nilsth
 * 
 */
public class UserInterfaceContainer extends IntrospectContainer {

	private LanguageProvider languageProvider;
	private final IntrospectApplication application;
	private UserInterfaceController<?> userInterfaceController;
	private DomainInfoProvider domainInfoProvider;

	public UserInterfaceContainer(IntrospectApplication application) {
		super(new ServiceContainer(application));
		this.application = application;
		add(application.getUserInterfaceControllerClass());
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
	 * Convenient method to get the {@link UserInterfaceController} FIXME: remove
	 * this method (user constructor injection for registered objects in
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
	 * Convenient method to get the {@link DomainInfoProvider} FIXME: remove
	 * this method (user constructor injection for registered objects in
	 * container instead)
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


}
