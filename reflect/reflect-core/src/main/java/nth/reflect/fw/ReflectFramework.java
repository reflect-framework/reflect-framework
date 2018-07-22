package nth.reflect.fw;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.container.exception.ReflectContainerException;
import nth.reflect.fw.container.exception.ReflectContainerInitializationException;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;

/**
 * <p>
 * The {@link ReflectFramework} is a light weight
 * <a href="http://en.wikipedia.org/wiki/Java_(programming_language)">Java</a>
 * <a href="http://en.wikipedia.org/wiki/Application_framework">Application
 * Framework</a> for creating
 * <a href="https://en.wikipedia.org/wiki/Business_software">business like
 * applications</a> that can view and manipulate information by using
 * <a href="https://en.wikipedia.org/wiki/Form_(document)">forms</a> and
 * <a href="https://en.wikipedia.org/wiki/Table_(information)">tables</a>.
 * </p>
 * <p>
 * With the {@link ReflectFramework} you only need to create
 * {@link DomainObject}s. The {@link ReflectFramework} provides you the
 * <a href="https://en.wikipedia.org/wiki/User_interface">user interface</a>,
 * without writing any <a href="https://en.wikipedia.org/wiki/User_interface">user interface</a> code. This means that the
 * {@link ReflectFramework} is ideal for rapid prototyping, rapid development
 * or to learn programming (e.g. to teach:
 * <a href="https://en.wikipedia.org/wiki/Object-oriented_programming">Object
 * Oriented Programming</a> or
 * <a href="https://en.wikipedia.org/wiki/Domain-driven_design">Domain Driven
 * Design</a>).
 * </p>
 * <p>
 * The {@link ReflectFramework} provides different
 * <a href="https://en.wikipedia.org/wiki/User_interface">user interface</a>
 * implementations that you can use for your {@link DomainObject}s. Please read the
 * {@link ReflectApplicationProjects} section to learn what
 * <a href="https://en.wikipedia.org/wiki/User_interface">user interfaces</a>
 * are available and how to get started. If you want to know more about the
 * details of the {@link ReflectFramework}, keep reading....
 * </p>
 * <h2>Why the Reflect Framework was developed</h2>
 * <p>
 * {@insert ReflectWhyItWasDeveloped}
 * </p>
 * 
 * <h2>Reflect Core Values</h2>
 * <p>
 * {@insert ReflectCoreValues}
 * </p>
 * 
 * <h2>Reflect License</h2>
 * <p>
 * {@insert ReflectLicense}
 * </p>
 * 
 * <h2>Architecture of an Reflect Application</h2>
 * <p>
 * {@insert ReflectArchitecture}
 * </p>
 * <h2>Dependency Injection Container</h2>
 * <p>
 * {@insert DependencyInjectionContainer}
 * </p>
 * 
 * <h2>The Reflect Application</h2>
 * <p>
 * {@insert ReflectApplication}
 * </p>
 * 
 * <h2>Starting a new Reflect Project</h2>
 * <p>
 * {@insert ReflectStartingANewProject}
 * </p>
 */

public class ReflectFramework {

	/**
	 * Initializes the {@link ReflectFramework} by creating the
	 * {@link UserInterfaceContainer} and therefore creating all
	 * {@link DependencyInjectionContainer} for all the application layers and
	 * finally starting the {@link UserInterfaceController} This method is
	 * normally called from the {@link ReflectApplication} class.
	 * 
	 * @throws ReflectContainerException
	 */
	public static void launch(ReflectApplication application) {
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
			ReflectApplication application) {
		UserInterfaceContainer userInterfaceContainer;
		try {
			userInterfaceContainer = new UserInterfaceContainer(application);
		} catch (Exception exception) {
			throw new ReflectContainerInitializationException(exception);
		}
		return userInterfaceContainer;
	}

}
