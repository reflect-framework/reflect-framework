package nth.reflect.fw.gui.layer5provider.actionmethod.execution;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public abstract class ActionMethodExecutionProvider
		implements nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	public static final TranslatableString ERROR_EXECUTE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.dialog.message", "Failed to execute.");

	@Override
	public void execute(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		// TODO show ProgressDialog
		TranslatableString title = methodInfo.getTitle(methodParameter);
//		showProgressDialog(title, PERCENT_0, PERCENT_100);

		// start method execution thread
		try {
			startMethodExecutionThread(container, methodOwner, methodInfo, methodParameter);
		} catch (Exception exception) {
			TranslatableString message = ERROR_EXECUTE;
			UserInterfaceController userInterface = container.get(UserInterfaceController.class);
			userInterface.showError(title, message, exception);
		}
	}

	/**
	 * This method is called from
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)} <br>
	 * This method must do 3 things<br>
	 * - invoke Object methodReturnValue=
	 * {@link ActionMethodInfo#invoke(Object, Object)} in a separate thread (may be
	 * time consuming). This will need to be implemented per user interface because
	 * threading may need to be differently implemented for each user interface
	 * framework. <br>
	 * - catch errors during the execution of the thread and call
	 * {@link #showError(String, String, Throwable)} if needed<br>
	 * - invoke
	 * {@link GraphicalUserinterfaceController#processActionMethodReturnValue(Object, ActionMethodInfo, Object, Object)}
	 * <br>
	 * <br>
	 * This method can be overridden if the framework of the user interface
	 * implementation requires a specific threading mechanism (i.e. Android)
	 * 
	 * @param methodOwner
	 * @param actionMethodInfo
	 * @param methodParameter
	 * 
	 */

	public void startMethodExecutionThread(UserInterfaceContainer container, final Object methodOwner,
			final ActionMethodInfo actionMethodInfo, final Object methodParameter) {

		GraphicalUserinterfaceController userInterface = container.get(GraphicalUserinterfaceController.class);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Object methodReturnValue = actionMethodInfo.invoke(methodOwner, methodParameter);

					// Calling a method on a object can change its state
					// We therefore must update current tab
					Tab selectedTab = userInterface.getTabs().getSelected();
					if (selectedTab != null) {
						selectedTab.onRefresh();
					}
					// process method result
					actionMethodInfo.processResult(container, methodOwner, methodParameter, methodReturnValue);
				} catch (Exception exception) {
					TranslatableString title = actionMethodInfo.getTitle(methodParameter);
					TranslatableString message = ERROR_EXECUTE;
					userInterface.showError(title, message, exception);
				}

			}
		};
		executeInThread(runnable);
	}

	/**
	 * Hook so that each type of user interface can execute the method using
	 * threading in they way preferred by user interface
	 * 
	 * @param methodExecutionRunnable
	 */
	public abstract void executeInThread(Runnable methodExecutionRunnable);

}
