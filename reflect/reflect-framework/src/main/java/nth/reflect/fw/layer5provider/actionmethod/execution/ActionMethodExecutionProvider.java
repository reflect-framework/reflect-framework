package nth.reflect.fw.layer5provider.actionmethod.execution;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public abstract class ActionMethodExecutionProvider implements Provider {

	private static final TranslatableString ERROR_DIALOG_TITLE = new TranslatableString(
			ActionMethodInfo.class.getCanonicalName() + ".error.dialog.title", "Error while processing an action");
	private static final TranslatableString ERROR_DIALOG_MESSAGE = new TranslatableString(
			ActionMethodInfo.class.getCanonicalName() + ".error.dialog.message", "Action: %s");

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

	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) {

		try {
			if (methodInfo.needsToCreateParameter(methodParameter)) {
				methodParameter = methodInfo.createMethodParameter(methodOwner);
			}

			System.out.println(":" + methodParameter);
			ActionMethodPreHandler handler = methodInfo.getPreHandler();
			handler.preProcess(container, methodInfo, methodOwner, methodParameter);

		} catch (Throwable throwable) {
			TranslatableString title = ERROR_DIALOG_TITLE;
			TranslatableString actionMethodTitle = methodInfo.getTitle(methodParameter);
			TranslatableString message = ERROR_DIALOG_MESSAGE.withParameters(actionMethodTitle);
			UserInterfaceController userInterface = container.get(UserInterfaceController.class);
			userInterface.showError(title, message, throwable);
		}

	}

	/**
	 * This method is called from the {@link ActionMethodInvoker#run()} method to
	 * ensure that a {@link ActionMethod} is being processed in they way preferred
	 * by user interface. Most user interface frameworks have a specific way to
	 * execute threads so that they work correctly with the life cycle of the user
	 * interface framework.
	 * 
	 * @param methodExecutionRunnable
	 */
	public abstract void executeOnUiThread(Runnable methodProcessing);
}
