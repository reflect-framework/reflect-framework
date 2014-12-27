package nth.introspect;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.container.exception.MissingServiceClassException;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.definition.DomainLayer;
import nth.introspect.definition.InfrastructureLayer;
import nth.introspect.definition.ServiceLayer;
import nth.introspect.definition.UserInterfaceLayer;
import nth.introspect.provider.Provider;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.dataaccess.DataAccessProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * Introspect is a light weight application framework. It provides a framework
 * with basic functionalities that can be used for developing most applications.<br>
 * 
 * <h1>The Introspect is designed on the following core values:</h1>
 * 
 * <ul>
 * <li>Based on the <a href="http://en.wikipedia.org/wiki/Naked_objects">Naked
 * Objects</a> <a
 * href="http://en.wikipedia.org/wiki/Software_design_pattern">Design
 * Principle</a>:</li>
 * <ul>
 * <li>All business logic should be encapsulated onto the domain objects. This
 * principle is not unique to naked objects: it is just a strong commitment to
 * encapsulation.</li>
 * <li>The user interface should be a direct representation of the domain
 * objects, with all user actions consisting, explicitly, of creating or
 * retrieving domain objects and/or invoking methods on those objects. This
 * principle is also not unique to naked objects: it is just a specific
 * interpretation of an object-oriented user interface (OOUI).</li>
 * <li>The original idea in the naked objects pattern arises from the
 * combination of these two, to form the third principle: The user interface
 * should be created 100% automatically from the definition of the domain
 * objects using the technology of <a
 * href="http://en.wikipedia.org/wiki/Reflection_(computer_programming)"
 * >reflection</a></li>
 * </ul>
 * 
 * <li>Provide a good structure for applications:</li>
 * <ul>
 * <li>Separation of concerns by means of the <a
 * href="http://alistair.cockburn.us/Hexagonal+architecture">Ports and
 * Adapters</a> design pattern. In other words: simple interfaces (ports) for
 * different communication purposes (i.e. user interface or database) that can
 * have multiple implementations (adapters).</li>
 * <li>Example1: the {@link UserInterfaceProvider} can have a
 * SwingUserInterfaceProvider or a AndroidUserInterfaceProvider or a
 * SoapUserInterfaceProvider as implementation</li>
 * <li>Example2: the {@link DataAccessProvider} can have a JpaDataAccessProvider
 * or a SqlDataAccessProvider or a Db40DataAccessProvider as implementation</li>
 * </ul>
 * 
 * <li>Light weight and modular:</li>
 * <ul>
 * <li>Simple to understand.</li>
 * <li>The core library will only be a few kilo bytes. You can add additional
 * libraries to add additional functionality (providers)</li>
 * </ul>
 * <li>Easy to extend:</li>
 * <ul>
 * <li>Modifying the framework to your own liking (preferably by extending
 * providers)</li>
 * <li>Adding new functionalities by writing your own providers (implementing a
 * port)</li>
 * </ul>
 * </ul> <h1>Separation of concerns</h1> There are many ideas on how an ideal
 * application architecture should look like. They all have the same objective:
 * <a href="http://en.wikipedia.org/wiki/Separation_of_concerns">separation of
 * concerns</a>. They all achieve this separation by dividing the software into
 * layers. Each has at least one layer for the domain logic, and one or more
 * layers for interfaces. Each of these architectures produce systems that are:
 * <ul>
 * <li>Independent of frameworks: The domain layer (domain and service objects)
 * to be independent from any Framework.</li>
 * <li>Independent of the UI: The UI can change easily, without chaning the rest
 * of the application. A UI for the WEB should easily be replaced by a desktop
 * application</li>
 * <li>Independent of the Database:</li>
 * <li>Independent of any other external element:</li>
 * <li>Testable: The domain layer can be tested without the user interface (UI),
 * database, web server or any other external element.</li>
 * </ul>
 * 
 * <h1>Ports and Adapters</h1> The introspect framework achieves separation of
 * concerns by means of the ports and adapters design pattern. For more
 * information see {@link Provider} for more information.
 * 
 * <h1>The Introspect class</h1> The Introspect framework can be accessed by the
 * static class {@link Introspect}. The Introspect class holds references to
 * several ports with adapters. <br>
 * <h1>Initializing the Introspect framework</h1> The Introspect framework is
 * initialized once by calling {@link Introspect#init(IntrospectApplication)}
 * when an application is started.<br>
 * The init parameter {@link IntrospectApplication} is a class that will
 * coordinate the initialization of an application<br>
 * Each application type (command line, Swing, Android, Vaadin, etc..) has its
 * own implementation of IntrospectInitializer to help initializing the
 * framework.<br>
 * See the type hierarchy of {@link IntrospectApplication} to learn which
 * classes can be used and view their java doc to learn how to use them.<br>
 * <br>
 * 
 * @author Nils ten Hoeve
 * @author Some paragraphs where inspired (or almost bluntly copied) from Uncle
 *         Bob Martins article <a href=
 *         "http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html"
 *         >The clean architecture</a>
 */

// TODO remove the need for static lookups
// TODO Introspect as object with IntrospectApplication as constructor parameter
// (replace initializer and start methods)
// TODO rename Introspect to Reflect and ReflectFramework
// TODO update javadoc (or refer to web site)
public class Introspect {

	private static IntrospectContainer introspectContainer;
	private static PathProvider pathProvider;
	private static LanguageProvider languageProvider;
	// private static DomainInfoProvider domainInfoProvider;
	private static UserInterfaceProvider<?> userInterfaceProvider;

	public static void init(IntrospectApplication application) {
		try {
			introspectContainer = new UserInterfaceContainer(application);
		} catch (Exception exception) {
			throw new IntrospectContainerInitializationException(exception);
		}
	}

	/**
	 * Starts the application by getting/ creating the UserInterfaceProvider
	 * from the reflect container
	 * 
	 * @throws IntrospectContainerException
	 */
	public static void start() {
		// TODO remove init method and add following lines
		// try {
		// introspectContainer = new UserInterfaceContainer(application);
		// } catch (Exception exception) {
		// throw new IntrospectContainerInitializationException(exception);
		// }

		userInterfaceProvider= (UserInterfaceProvider<?>) introspectContainer.get(UserInterfaceProvider.class);
		userInterfaceProvider.start();
	}

	// TODO get rid of this service lookup, use dependency injection instead
	public static PathProvider getPathProvider() {
		if (pathProvider == null) {
			pathProvider = (PathProvider) introspectContainer
					.get(PathProvider.class);

		}
		return pathProvider;
	}

	// TODO get rid of this service lookup, use dependency injection instead
	public static LanguageProvider getLanguageProvider() {
		if (languageProvider == null) {
			languageProvider = (LanguageProvider) introspectContainer
					.get(LanguageProvider.class);
		}
		return languageProvider;
	}

}
