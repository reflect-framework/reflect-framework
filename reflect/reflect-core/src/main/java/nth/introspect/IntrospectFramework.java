package nth.introspect;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.path.url.ReflectUrlStreamHandlerFactory;

/**
 * <p>
 * Introspect is a light weight
 * <a href="http://en.wikipedia.org/wiki/Java_(programming_language)">Java</a>
 * <a href="http://en.wikipedia.org/wiki/Application_framework">Application
 * Framework</a>. It provides a framework for writing business like applications
 * for viewing and editing information in forms and tables (not for graphical
 * applications such as games)
 * </p>
 * <p>
 * With the introspect framework you only need to create domain classes.
 * Introspect provides you the user interface, without writing any user
 * interface code. This means is that the Introspect Framework is ideal for
 * rapid prototyping, rapid development or to learn programming (e.g. at schools
 * to teach:
 * <a href="https://en.wikipedia.org/wiki/Object-oriented_programming">Object
 * Oriented Programming</a> or
 * <a href="https://en.wikipedia.org/wiki/Domain-driven_design">Domain Driven
 * Design</a>).
 * </p>
 * <p>
 * The Introspect framework provides different user interface implementations
 * that you can use for:
 * <ul>
 * <li>The desktop (based on
 * <a href="http://en.wikipedia.org/wiki/Swing_(Java)">Swing</a>)</li>
 * <li>Mobile devices (based on
 * <a href="http://en.wikipedia.org/wiki/Android_(operating_system)">Android</a>
 * )</li>
 * <li>The web (based on
 * <a href="http://en.wikipedia.org/wiki/Vaadin">Vaadin</a>)</li>
 * <li><a href="http://en.wikipedia.org/wiki/Command-line_interface">Command
 * line</a></li>
 * <li>And others</li>
 * </ul>
 * </p>
 * <p>
 * If you want to start coding right now (even if you are a beginner) please go
 * to the {@link IntrospectGettingStarted} section. If you want to know more
 * about the Introspect Framework keep reading....
 * </p>
 * <h2>Why the Introspect framework was developed</h2>
 * {@insert IntrospectWhyItWasDeveloped}
 * 
 * <h2>Introspect Core Values</h2> {@insert IntrospectCoreValues}
 * 
 * <h2>Introspect License</h2> {@insert IntrospectLicense}
 * 
 * <h2>Architecture of an introspect application</h2>
 * {@insert IntrospectArchitecture}
 * 
 * <h2>Dependency Injection Container</h2>
 * {@insert DependencyInjectionContainer}
 * 
 * <h2>The Introspect Application</h2> {@insert IntrospectApplication}
 * 
 * <h2>Starting a new Introspect Project</h2>
 * {@insert IntrospectStartingANewProject}
 */

public class IntrospectFramework {

	/**
	 * Initializes the {@link IntrospectFramework} by creating the
	 * {@link UserInterfaceContainer} and therefore creating all
	 * {@link DependencyInjectionContainer} for all the application layers and
	 * finally starting the {@link UserInterfaceController} This method is
	 * normally called from the {@link IntrospectApplication} class.
	 * 
	 * @throws IntrospectContainerException
	 */
	public static void launch(IntrospectApplication application) {
		new ReflectUrlStreamHandlerFactory(application).register(); 
		
		UserInterfaceContainer userInterfaceContainer = createUserInterfaceContainer(application);
		UserInterfaceController userInterfaceController = userInterfaceContainer
				.get(UserInterfaceController.class);
		userInterfaceController.launch();
	}

	/**
	 * Creates the {@link DependencyInjectionContainer}s for the different
	 * layers.<br>
	 * Each {@link DependencyInjectionContainer} represent a layer.<br>
	 * Each {@link DependencyInjectionContainer} has an inner
	 * {@link DependencyInjectionContainer} that represent a lower layer
	 * (dependencies to lower layer objects only).<br>
	 * The {@link UserInterfaceContainer} is the outer
	 * {@link DependencyInjectionContainer} that contains all inner containers.
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
