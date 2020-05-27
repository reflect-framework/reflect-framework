package nth.reflect.fw.layer1userinterface.controller;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.notification.NotificationListener;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

//FIXME: test all methods in this class

/**
 *
 * <p>
 * The {@link UserInterfaceController} is responsible for showing information to
 * the user (or other systems) and processing the information from the user
 * using the objects in the lower layers (see {@link ReflectArchitecture}).
 * </p>
 * 
 * <p>
 * The {@link UserInterfaceController} provides a basic interface for handling
 * communication with other systems (e.g. a
 * <a href="https://simple.wikipedia.org/wiki/SOAP_(protocol)">SOAP</a> or
 * <a href=
 * "https://en.wikipedia.org/wiki/Representational_state_transfer">RESTFULL
 * interface</a>) or with a person or other system via a
 * <a href="https://en.wikipedia.org/wiki/Command-line_interface">command line
 * interface</a>.
 * </p>
 * 
 * @author Nils ten Hoeve
 * 
 */
public abstract class UserInterfaceController implements NotificationListener {

	public static final TranslatableString ERROR_DIALOG_TITLE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + "error.dialog.title", "Error");
	public static final TranslatableString ERROR_SHOW_RESULT = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.show.result",
			"Error while displaying an action result: %s");
	public static final TranslatableString ERROR_EXECUTE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.dialog.message", "Failed to execute.");
	public static final TranslatableString CONFIRMATION_DIALOG_TITLE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".confirmation.dialog.title", "Confirmation");
	public static final TranslatableString CONFIRMATION_DIALOG_QUESTION = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".confirmation.dialog.question",
			"Do you want to execute: %s ?");

	protected final ReflectionProvider reflectionProvider;
	protected final LanguageProvider languageProvider;
	protected final UserInterfaceContainer container;

	public UserInterfaceController(UserInterfaceContainer container) {
		this.container = container;
		this.reflectionProvider = container.get(ReflectionProvider.class);
		this.languageProvider = container.get(LanguageProvider.class);
	}

	public abstract void showError(TranslatableString title, TranslatableString message, Throwable throwable);

	/**
	 * Provides simple feedback about an operation
	 * 
	 * @param message
	 */
	public abstract void showMessage(TranslatableString message);

	/**
	 * Allows the user interface objects to be build (i.e. the creation of a main
	 * window). All information needed to start the application (like the command
	 * line arguments or web application URL) should be available in the
	 * {@link ReflectApplication}
	 */
	public abstract void launch();

	public UserInterfaceContainer getUserInterfaceContainer() {
		return container;
	}
}
