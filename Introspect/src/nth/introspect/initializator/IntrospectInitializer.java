package nth.introspect.initializator;

import nth.introspect.Introspect;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.info.InfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * The Introspect framework is initialized once by calling {@link Introspect#init(IntrospectInitializer)} when an application is started.<br>
 * The init parameter {@link IntrospectInitializer} is a class that will coordinate the initialization of an application<br>
 * Each application type (command line, Android, Vaadin, etc..) has its own implementation of IntrospectInitializer to help initializing the framework.<br>
 * See the type hierarchy of {@link IntrospectInitializer} to learn which classes can be used and view their java doc to learn how to use them.<br>
 * 
 * @author nilsth
 * 
 */




public interface IntrospectInitializer {

	DomainProvider createDomainProvider();

	AuthorizationProvider createAuthorizationProvider();

	ValidationProvider createValidationProvider();

	LanguageProvider createLanguageProvider();

	InfoProvider createInfoProvider();

	PathProvider createPathProvider();

	UserInterfaceProvider<?> createUserInterfaceProvider();
	
	void addServiceClass(Class<?> serviceClass);

}
