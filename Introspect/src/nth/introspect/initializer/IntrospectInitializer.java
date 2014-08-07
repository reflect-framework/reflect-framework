package nth.introspect.initializer;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;
import nth.introspect.provider.version.VersionProvider;

//TODO refactor to a IntrospectApplication interface

/**
 * {@link IntrospectInitializer} is used as initialization parameter for the {@link Introspect}.<br>
 * The implementation of {@link IntrospectInitializer}:
 * A Introspect application service several purposes:
 * <ul>
 * <li>It creates the providers needed for the application. Each application type (commandline, swing, vaadin) requires different implementations of the providers</li>
 * <li>It provides a list of front end service objects, that basicaly provides the actionable/menu items</li>
 * <li>It provides a list of back end service objects, that basicaly communicate to the outside world (i.e. data base access objects, email clients, soap clients, etc)</li>
 * </ul>
 * Each application type (command line, Android, Vaadin, etc..) has its own implementation of {@link IntrospectInitializer} to help initializing the framework.<br>
 * See the type hierarchy of {@link IntrospectInitializer} to learn which classes can be used and view their java doc to learn how to use them.<br>
 * <br>
 * @author nilsth
 * 
 */


//TODO rename Provider to Module???
public abstract class IntrospectInitializer {

	private final Object application;
	private final List<Class<?>> frontEndServiceClasses;
	private final List<Class<?>> backEndServiceClasses;

	public IntrospectInitializer(Object application) {
		this.application = application;
		this.frontEndServiceClasses=new ArrayList<Class<?>>();
		this.backEndServiceClasses=new ArrayList<Class<?>>();
	}
	
	public abstract UserInterfaceProvider<?> createUserInterfaceProvider();
	
	public abstract DomainProvider createDomainProvider();
	
	public abstract VersionProvider createVersionProvider();
	
	public abstract PathProvider createPathProvider();
	
	public abstract LanguageProvider createLanguageProvider();
	
	public abstract AuthorizationProvider createAuthorizationProvider();
		
	public abstract ValidationProvider createValidationProvider();


	public void registerFrontEndServiceClass(Class<?> frontEndServiceClass) {
		frontEndServiceClasses.add(frontEndServiceClass);
	}
	
	public void registerBackEndServiceClass(Class<?> frontEndServiceClass) {
		frontEndServiceClasses.add(frontEndServiceClass);
	}

	public List<Class<?>> getFrontEndServiceClasses() {
		return frontEndServiceClasses;
	}
	
	public List<Class<?>> getBackEndServiceClasses() {
		return backEndServiceClasses;
	}

	public Object getApplication() {
		return application;
	}
	
	
	
}
