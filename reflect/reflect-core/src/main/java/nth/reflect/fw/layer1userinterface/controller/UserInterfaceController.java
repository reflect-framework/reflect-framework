package nth.reflect.fw.layer1userinterface.controller;

import java.util.Optional;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.notification.NotificationListener;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

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
	public static final TranslatableString ERROR_OPEN_URI = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.open.uri", "Error browsing URI: %s");
	public static final TranslatableString ERROR_SAVE_FILE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.save.file", "Failed to save file.");
	public static final TranslatableString ERROR_OPEN_FILE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.open.file", "Failed to open file.");
	public static final TranslatableString ERROR_EXECUTE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.dialog.message", "Failed to execute.");
	public static final TranslatableString RESULT_DIALOG_MESSAGE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".result.dialog.message", "%s: Result is: %s");
	public static final TranslatableString CONFIRMATION_DIALOG_TITLE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".confirmation.dialog.title", "Confirmation");
	public static final TranslatableString CONFIRMATION_DIALOG_QUESTION = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".confirmation.dialog.question",
			"Do you want to execute: %s ?");
	private static final TranslatableString DISPLAY_ERROR_DIALOG_TITLE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".display.error.dialog.title",
			"Error while displaying an action result");
	private static final TranslatableString DISPLAY_ERROR_DIALOG_MESSAGE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".display.error.dialog.message", "Action: %s");

	protected final ReflectionProvider reflectionProvider;
	protected final LanguageProvider languageProvider;
	protected final UserInterfaceContainer userInterfaceContainer;

	public UserInterfaceController(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
	}

	/**
	 * This method is called when a user sends an command to the
	 * {@link UserInterfaceController}. this can come from different sources such
	 * as:
	 * <ul>
	 * <li>command line</li>
	 * <li>graphical user interface (when the user activates a menu item)</li>
	 * <li>http request from a SOAP or Restfull client</li>
	 * <li>etc</li>
	 * </ul>
	 * This method will process the {@link ActionMethod} parameter (depending on how
	 * it is annotated):
	 * <ul>
	 * <li>{@link ExecutionModeType#EXECUTE_METHOD_DIRECTLY }: Will call
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 * directly (i.e. when there is no {@link ActionMethod} parameter)</li>
	 * <li>{@link ExecutionModeType#EXECUTE_METHOD_AFTER_CONFORMATION }: Will ask
	 * the user for confirmation before the {@link ActionMethod} is executed. To do
	 * this it will call one of the confirmActionMethodParameter(...) methods in the
	 * {@link UserInterfaceController} implementation. After the confirmation the
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)} needs
	 * to be called (i.e. by a OK button).</li>
	 * <li>{@link ExecutionModeType#EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL }:
	 * Will let the user edit the {@link ActionMethod} parameter before the
	 * {@link ActionMethod} is executed. To do this it will call one of the
	 * editActionMethodParameter(...) methods in the {@link UserInterfaceController}
	 * implementation. After the confirmation the
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)} needs
	 * to be called (i.e. by a OK button).</li>
	 * </ul>
	 * 
	 * @param methodOwner     Domain or service object that owns the method
	 * @param methodInfo      {@link ActionMethodInfo} contains information on an
	 *                        {@link ActionMethod}
	 * @param methodParameter The value of the {@link ActionMethod} parameter
	 */

	public void processActionMethod(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		try {

			if (methodParameter == null && methodInfo.hasParameter() || methodInfo.hasParameterFactory()) {
				methodParameter = methodInfo.createMethodParameter(methodOwner);
			}

			ExecutionModeType executionMode = methodInfo.getExecutionMode();
			switch (executionMode) {
			case EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL:
				methodInfo.invokeEditParameterMethod(this, methodOwner, methodParameter);
				break;
			case EXECUTE_METHOD_AFTER_CONFORMATION:
				methodInfo.invokeConfirmMethod(this, methodOwner, methodParameter);
				break;
			case EXECUTE_METHOD_DIRECTLY:
				processActionMethodExecution(methodOwner, methodInfo, methodParameter);
				break;
			}
		} catch (Throwable throwable) {
			TranslatableString title = DISPLAY_ERROR_DIALOG_TITLE;
			Optional<Object> optionalMethodParameter = Optional.ofNullable(methodParameter);
			TranslatableString actionMethodTitle = methodInfo.getTitle(optionalMethodParameter);
			TranslatableString message = DISPLAY_ERROR_DIALOG_MESSAGE.withParameters(actionMethodTitle);
			showError(title, message, throwable);
		}

	}

	/**
	 * This method is called from
	 * {@link #processActionMethod(Object, ActionMethodInfo, Object)} or from the
	 * {@link FormOkItem} linked to the OK button <br>
	 * It needs the check if the method is enabled before the method is executed
	 * <br>
	 * It needs to validate the method parameter value before the method is executed
	 * 
	 * @param methodOwner          Domain or service object that owns the method
	 * @param actionMethodInfo     {@link ActionMethodInfo} contains information on
	 *                             an {@link ActionMethod}
	 * @param methodParameterValue The value of the {@link ActionMethod} parameter
	 * @throws Exception
	 * 
	 */

	public abstract void processActionMethodExecution(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter);

	/**
	 * This method is called from
	 * {@link #startMethodExecutionThread(Object, ActionMethodInfo, Object)} when
	 * the thread is completed.<br>
	 * It will open a new Tab or InfoDialog to show the method return value
	 * 
	 * 
	 * @param serviceObject
	 * @param actionMethodInfo
	 * @param optionalMethodParameter
	 * @param optionalMethodResult
	 */

	public void processActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			Object methodResult) {
		try {
			methodInfo.processResult(userInterfaceContainer, methodOwner, methodParameter, methodResult);
		} catch (Throwable exception) {
			TranslatableString title = DISPLAY_ERROR_DIALOG_TITLE;
			TranslatableString message = DISPLAY_ERROR_DIALOG_MESSAGE.withParameters(methodParameter);
			showError(title, message, exception);
		}

	}

	public abstract void showError(TranslatableString title, TranslatableString message, Throwable throwable);

	/**
	 * Provides simple feedback about an operation
	 * 
	 * @param message
	 */
	public abstract void showMessage(TranslatableString message);

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public abstract void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			String methodResult);

	/**
	 * Allows the user interface objects to be build (i.e. the creation of a main
	 * window). All information needed to start the application (like the command
	 * line arguments or web application URL) should be available in the
	 * {@link ReflectApplication}
	 */
	public abstract void launch();

	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}
}
