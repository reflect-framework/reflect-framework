package nth.introspect;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;

/**
 * <p>
 * The {@link IntrospectFramework} is a light weight
 * <a href="http://en.wikipedia.org/wiki/Java_(programming_language)">Java</a>
 * <a href="http://en.wikipedia.org/wiki/Application_framework">Application
 * Framework</a> for creating
 * <a href="https://en.wikipedia.org/wiki/Business_software">business like
 * applications</a> that can view and manipulate information by using
 * <a href="https://en.wikipedia.org/wiki/Form_(document)">forms</a> and
 * <a href="https://en.wikipedia.org/wiki/Table_(information)">tables</a>.
 * </p>
 * <p>
 * With the {@link IntrospectFramework} you only need to create
 * {@link DomainObject}s. The {@link IntrospectFramework} provides you the
 * <a href="https://en.wikipedia.org/wiki/User_interface">user interface</a>,
 * without writing any <a href="https://en.wikipedia.org/wiki/User_interface">user interface</a> code. This means that the
 * {@link IntrospectFramework} is ideal for rapid prototyping, rapid development
 * or to learn programming (e.g. to teach:
 * <a href="https://en.wikipedia.org/wiki/Object-oriented_programming">Object
 * Oriented Programming</a> or
 * <a href="https://en.wikipedia.org/wiki/Domain-driven_design">Domain Driven
 * Design</a>).
 * </p>
 * <p>
 * The {@link IntrospectFramework} provides different
 * <a href="https://en.wikipedia.org/wiki/User_interface">user interface</a>
 * implementations that you can use for your {@link DomainObject}s. Please read the
 * {@link IntrospectApplicationProjects} section to learn what
 * <a href="https://en.wikipedia.org/wiki/User_interface">user interfaces</a>
 * are available and how to get started. If you want to know more about the
 * details of the {@link IntrospectFramework}, keep reading....
 * </p>
 * <h2>Why the Introspect framework was developed</h2>
 * <p>
 * {@insert IntrospectWhyItWasDeveloped}
 * </p>
 * 
 * <h2>Introspect Core Values</h2>
 * <p>
 * {@insert IntrospectCoreValues}
 * </p>
 * 
 * <h2>Introspect License</h2>
 * <p>
 * {@insert IntrospectLicense}
 * </p>
 * 
 * <h2>Architecture of an introspect application</h2>
 * <p>
 * {@insert IntrospectArchitecture}
 * </p>
 * <h2>Dependency Injection Container</h2>
 * <p>
 * {@insert DependencyInjectionContainer}
 * </p>
 * 
 * <h2>The Introspect Application</h2>
 * <p>
 * {@insert IntrospectApplication}
 * </p>
 * 
 * <h2>Starting a new Introspect Project</h2>
 * <p>
 * {@insert IntrospectStartingANewProject}
 * </p>
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
