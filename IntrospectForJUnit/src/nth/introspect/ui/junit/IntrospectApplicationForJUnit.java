package nth.introspect.ui.junit;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;














import javafx.collections.SetChangeListener;
import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.InfrastructureContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.about.DefaultAboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.provider.domain.info.DefaultDomainInfoProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.DefaultLanguageProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.notification.DefaultNotificationProvider;
import nth.introspect.provider.notification.NotificationProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.validation.DefaultValidationProvider;
import nth.introspect.provider.validation.ValidationProvider;


public  class IntrospectApplicationForJUnit implements IntrospectApplication {

	
	private final IntrospectContainer topContainer;
	private final List<Class<?>> serviceClasses;
	private final List<Class<?>> infrastructureClasses;
	private final Class<? extends IntrospectApplication> introspectApplicationClass;

	/**
	 * Constructur if you only want to test infrastructure classes
	 * @param introspectApplicationClass needed to set the right path for the pathProvider
	 * @param infrastructureClasses
	 */
	public IntrospectApplicationForJUnit(Class<? extends IntrospectApplication> introspectApplicationClass, List<Class<?>> infrastructureClasses) {
		this(introspectApplicationClass, null, infrastructureClasses);
	}
	
	public IntrospectApplicationForJUnit(Class<? extends IntrospectApplication> introspectApplicationClass, List<Class<?>> serviceClasses, List<Class<?>> infrastructureClasses) {
		this.introspectApplicationClass = introspectApplicationClass;
		this.serviceClasses = serviceClasses;
		this.infrastructureClasses = infrastructureClasses;
		if (serviceClasses==null || serviceClasses.isEmpty()) {
			topContainer = new InfrastructureContainer(this);
		} else {
			topContainer = new ServiceContainer(this);
		}
	}
	
	

	public Object get(Class<?> type) {
		return topContainer.get(type);
	}

	@Override
	public Class<? extends UserInterfaceController<?>> getUserInterfaceControllerClass() {
		return null;// JUnit has no user interface controller
	}

	@Override
	public Class<? extends DomainInfoProvider> getDomainInfoProviderClass() {
		return DefaultDomainInfoProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getVersionProviderClass() {
		return DefaultAboutProvider.class;
	}

	@Override
	public Class<? extends PathProvider> getPathProviderClass() {
		return PathProviderForJUnit.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return serviceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return infrastructureClasses;
	}


	public Class<IntrospectApplication> getIntrospectApplicationClass() {
		return (Class<IntrospectApplication>) introspectApplicationClass;
	}

	
}
