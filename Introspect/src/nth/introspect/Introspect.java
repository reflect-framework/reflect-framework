package nth.introspect;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;

/**
 * The Introspect class is responsible for initializing the frame work and
 * creating all its objects. The Introspect framework is initialized with the
 * {@link Introspect#start(IntrospectApplication)} method which is called from a
 * class that extends the {@link IntrospectApplication}. <br>
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
