package nth.introspect.application;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * {@link IntrospectApplication} is used as initialization parameter for the {@link Introspect}.<br>
 * The implementation of {@link IntrospectApplication}:
 * A Introspect application service several purposes:
 * <ul>
 * <li>It provides the name, icon and description of the application (see object behavior)</li>
 * <li>It provides the location of the distribution package (jar, war)</li>
 * <li>It provides the providers needed for the application. Each application type (commandline, swing, vaadin) requires different implementations of the providers</li>
 * <li>It provides a list of front end service objects, that basically provides the actionable/menu items</li>
 * <li>It provides a list of back end service objects, that basically communicate to the outside world (i.e. data base access objects, email clients, soap clients, etc)</li>
 * </ul>
 * Each application type (command line, Android, Vaadin, etc..) has its own implementation of {@link IntrospectApplication} to help initializing the framework.<br>
 * See the type hierarchy of {@link IntrospectApplication} to learn which classes can be used and view their java doc to learn how to use them.<br>
 * <br>
 * @author nilsth
 * 
 */



public interface IntrospectApplication {

	public Class<? extends UserInterfaceProvider<?>> getUserInterfaceProviderClass();
	
	public Class<? extends DomainInfoProvider> getDomainInfoProviderClass();
	
	public Class<? extends AboutProvider> getVersionProviderClass();
	
	public Class<? extends PathProvider> getPathProviderClass();
	
	public Class<? extends LanguageProvider> getLanguageProviderClass();
	
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass();
		
	public Class<? extends ValidationProvider> getValidationProviderClass();

	public List<Class<?>> getFrontEndServiceClasses();
	
	public List<Class<?>> getBackEndServiceClasses();	
	
	
}
