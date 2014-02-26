package nth.introspect;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.initializator.IntrospectInitializer;
import nth.introspect.provider.Provider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.dataaccess.DataAccessProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.info.InfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.report.ReportProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * Introspect is a light weight application framework. It provides a framework with basic functionalities that can be used for developing most applications.<br>
 * 
 * <h1>The Introspect is designed on the following core values:</h1>
 * 
 * <ul>
 * <li>Based on the <a href="http://en.wikipedia.org/wiki/Naked_objects">Naked Objects</a> <a href="http://en.wikipedia.org/wiki/Software_design_pattern">Design Principle</a>:</li>
 * <ul>
 * <li>All business logic should be encapsulated onto the domain objects. This principle is not unique to naked objects: it is just a strong commitment to encapsulation.</li>
 * <li>The user interface should be a direct representation of the domain objects, with all user actions consisting, explicitly, of creating or retrieving domain objects and/or invoking methods on those objects. This principle is also not unique to naked objects: it is just a specific interpretation of an object-oriented user interface (OOUI).</li>
 * <li>The original idea in the naked objects pattern arises from the combination of these two, to form the third principle: The user interface should be created 100% automatically from the definition of the domain objects using the technology of <a href="http://en.wikipedia.org/wiki/Reflection_(computer_programming)">reflection</a></li>
 * </ul>
 * 
 * <li>Provide a good structure for applications:</li>
 * <ul>
 * <li>Separation of concerns by means of the <a href="http://alistair.cockburn.us/Hexagonal+architecture">Ports and Adapters</a> design pattern. In other words: simple interfaces (ports) for different communication purposes (i.e. user interface or database) that can have multiple implementations (adapters).</li>
 * <li>Example1: the {@link UserInterfaceProvider} can have a SwingUserInterfaceProvider or a AndroidUserInterfaceProvider or a SoapUserInterfaceProvider as implementation</li>
 * <li>Example2: the {@link DataAccessProvider} can have a JpaDataAccessProvider or a SqlDataAccessProvider or a Db40DataAccessProvider as implementation</li>
 * </ul>
 * 
 * <li>Light weight and modular:</li>
 * <ul>
 * <li>Simple to understand.</li>
 * <li>The core library will only be a few kilo bytes. You can add additional libraries to add additional functionality (providers)</li>
 * </ul>
 * <li>Easy to extend:</li>
 * <ul>
 * <li>Modifying the framework to your own liking (preferably by extending providers)</li>
 * <li>Adding new functionalities by writing your own providers (implementing a port)</li>
 * </ul>
 * </ul> <h1>Separation of concerns</h1>
 * There are many ideas on how an ideal application architecture should look like. They all have the same objective: <a href="http://en.wikipedia.org/wiki/Separation_of_concerns">separation of concerns</a>. They all achieve this separation by dividing the software into layers. Each has at least one layer for the domain logic, and one or more layers for interfaces. Each of these architectures
 * produce systems that are:
 * <ul>
 * <li>Independent of frameworks: The domain layer (domain and service objects) to be independent from any Framework.</li>
 * <li>Independent of the UI: The UI can change easily, without chaning the rest of the application. A UI for the WEB should easily be replaced by a desktop application</li>
 * <li>Independent of the Database:</li>
 * <li>Independent of any other external element:</li>
 * <li>Testable: The domain layer can be tested without the user interface (UI), database, web server or any other external element.</li>
 * </ul>
 * 
 * <h1>Ports and Adapters</h1> The introspect framework achieves separation of concerns by means of the ports and adapters design pattern. For more information see {@link Provider} for more information.
 * 
 * <h1>The Introspect class</h1> The Introspect framework can be accessed by the static class {@link Introspect}. The Introspect class holds references to several ports with adapters. <br>
 * <h1>Initializing the Introspect framework</h1> The Introspect framework is initialized once by calling {@link Introspect#init(IntrospectInitializer)} when an application is started.<br>
 * The init parameter {@link IntrospectInitializer} is a class that will coordinate the initialization of an application<br>
 * Each application type (command line, Swing, Android, Vaadin, etc..) has its own implementation of IntrospectInitializer to help initializing the framework.<br>
 * See the type hierarchy of {@link IntrospectInitializer} to learn which classes can be used and view their java doc to learn how to use them.<br>
 * <br>
 * 
 * @author Nils ten Hoeve
 * @author Some paragraphs where inspired (or almost bluntly copied) from Uncle Bob Martins article <a href="http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html">The clean architecture</a>
 */

// TODO explain what a port is in javadoc
// TODO dicide to use adapters or ports and refactor consistantly

public class Introspect {

	private static LanguageProvider languageProvider;
	private static PathProvider pathProvider;
	private static AuthorizationProvider authorizationProvider;
	private static DomainProvider domainProvider;
	private static UserInterfaceProvider<?> userInterfaceProvider;
	private static InfoProvider infoProvider;
	private static ValidationProvider validationProvider;
	private static List<DataAccessProvider<?>> dataAccessProviders=new ArrayList<DataAccessProvider<?>>();
	private static List<ReportProvider<?>> reportProviders;

	public static void init(IntrospectInitializer initializer) {
		pathProvider = initializer.createPathProvider();
		languageProvider = initializer.createLanguageProvider();
		authorizationProvider = initializer.createAuthorizationProvider();
		validationProvider = initializer.createValidationProvider();
		domainProvider = initializer.createDomainProvider();
		infoProvider = initializer.createInfoProvider();
		userInterfaceProvider = initializer.createUserInterfaceProvider();
		dataAccessProviders=new ArrayList<DataAccessProvider<?>>();
		reportProviders=new ArrayList<ReportProvider<?>>();
	}

	public static UserInterfaceProvider<?> getUserInterfaceProvider() {
		return userInterfaceProvider;
	}

	public static PathProvider getPathProvider() {
		return pathProvider;
	}

	public static AuthorizationProvider getAuthorizationProvider() {
		return authorizationProvider;
	}

	public static DomainProvider getDomainProvider() {
		return domainProvider;
	}

	public static LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	public static InfoProvider getInfoProvider() {
		return infoProvider;
	}

	public static ValidationProvider getValidationProvider() {
		return validationProvider;
	}

	public static DataAccessProvider<?> getDataAccessProvider(Class<? extends DataAccessProvider<?>> dataAccessClass) {
		//try to find an existing dataAccessProvider 
		for (DataAccessProvider<?> dataAccessObject:dataAccessProviders) {
			if (dataAccessObject.getClass().equals(dataAccessClass)) {
				return dataAccessObject;
			}
		}
		//if not found: try to instantiate the dataAccessProvider 
		try {
			DataAccessProvider<?> dataAccessProvider = dataAccessClass.newInstance();
			dataAccessProviders.add(dataAccessProvider);
			return dataAccessProvider;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ReportProvider<?> getReportProvider(Class<? extends ReportProvider<?>> reportProviderClass) {
		//try to find an existing dataAccessProvider 
		for (ReportProvider<?> reportProvider:reportProviders) {
			if (reportProvider.getClass().equals(reportProviderClass)) {
				return reportProvider;
			}
		}
		//if not found: try to instantiate the dataAccessProvider 
		try {
			ReportProvider<?> reportProvider = reportProviderClass.newInstance();
			reportProviders.add(reportProvider);
			return reportProvider;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
