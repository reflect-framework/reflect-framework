package nth.introspect;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.definition.InfrastructureObject;
import nth.introspect.definition.IntrospectArchitecture;
import nth.introspect.definition.ServiceObject;
import nth.introspect.provider.Provider;

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
 * <li>See {@link IntrospectArchitecture}</li>
 * <li>Use simple interfaces and different implementations where needed (I.E.
 * see different implementations of {@link Provider} and
 * {@link UserInterfaceController})</li>
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
 * <li>Modifying the framework to your own liking (By extending one or more {@link Provider}s
 * )</li>
 * <li>Adding your functionalities by writing (or registering existing) {@link ServiceObject}s and
 * {@link InfrastructureObject}s</li>
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
 * 
 * <h1>The Introspect class</h1> The Introspect framework is initialized with
 * the {@link Introspect#start(IntrospectApplication)} method. The
 * {@link IntrospectApplication} parameter is a class that will provide the
 * information needed to initialize the Introspect Framework<br>
 * Each application type (command line, Swing, Android, Vaadin, etc..) has its
 * own implementation of {@link IntrospectApplication} to help initializing the
 * framework.<br>
 * See the type hierarchy of {@link IntrospectApplication} to learn which
 * classes can be used.<br>
 * <br>
 * 
 * @author Nils ten Hoeve
 */

// TODO rename Introspect to Reflect and ReflectFramework
public class Introspect {

	/**
	 * Creates all {@link IntrospectContainer}'s and starts the application by
	 * getting/ creating the {@link UserInterfaceController} from the
	 * {@link UserInterfaceContainer}
	 * 
	 * @throws IntrospectContainerException
	 */
	public static void start(IntrospectApplication application) {
		UserInterfaceContainer userInterfaceContainer = createUserInterfaceContainer(application);
		UserInterfaceController<?> userInterfaceController = userInterfaceContainer
				.getUserInterfaceController();
		userInterfaceController.start();
	}

	/**
	 * Creates the {@link IntrospectContainer}s for the different layers.<br>
	 * Each {@link IntrospectContainer} represent a layer.<br>
	 * Each {@link IntrospectContainer} has an inner {@link IntrospectContainer}
	 * that represent a lower layer (dependencies to lower layer objects only).<br>
	 * The {@link UserInterfaceContainer} is the outer
	 * {@link IntrospectContainer} that contains all inner containers.
	 * 
	 * @param application
	 * @return the created {@link UserInterfaceContainer}
	 */
	private static UserInterfaceContainer createUserInterfaceContainer(
			IntrospectApplication application) {
		UserInterfaceContainer userInterfaceContainer;
		try {
			userInterfaceContainer = new UserInterfaceContainer(application);
		} catch (Exception exception) {
			throw new IntrospectContainerInitializationException(exception);
		}
		return userInterfaceContainer;
	}

}
