package nth.reflect.fw.layer5provider.actionmethod.execution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionMethodInvoker implements Action {

	public static final TranslatableString FAILED_TO_EXECUTE_MESSAGE = new TranslatableString(
			ActionMethodInvoker.class.getCanonicalName() + ".error.message", "Failed to execute.");

	private final UserInterfaceContainer userInterfaceContainer;
	private final ActionMethodCallback callBack;
	private final ActionMethodInfo methodInfo;
	private final Object methodOwner;
	private final UserInterfaceController userInterface;
	private final Object methodParameter;
	private final ActionMethodExecutionProvider executionProvider;

	public ActionMethodInvoker(UserInterfaceContainer userInterfaceContainer, ActionMethodCallback callBack,
			ActionMethodInfo methodInfo, Object methodOwner, Object methodParameter) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodParameter = methodParameter;
		this.userInterface = userInterfaceContainer.get(UserInterfaceController.class);
		this.executionProvider = userInterfaceContainer.get(ActionMethodExecutionProvider.class);
		this.callBack = callBack;
		this.methodInfo = methodInfo;
		this.methodOwner = methodOwner;
	}

	public ActionMethodInvoker(UserInterfaceContainer userInterfaceContainer, ActionMethodInfo methodInfo,
			Object methodOwner, Object methodParameter) {
		this(userInterfaceContainer, methodInfo.getResultHandler(), methodInfo, methodOwner, methodParameter);
	}

	@Override
	public void run() {
		try {
			Runnable runnable = createRunnable();
			executionProvider.executeOnUiThread(runnable);
		} catch (Throwable throwable) {
			TranslatableString title = methodInfo.getTitle(methodParameter);
			userInterface.showError(title, FAILED_TO_EXECUTE_MESSAGE, throwable);
		}

	}

	private Runnable createRunnable() {
		return new Runnable() {

			@Override
			public void run() {
				try {
					// TODO check if the method is enabled before the method is executed
					// (otherwise throw exception)
					// TODO validate the method parameter value before the method is
					// executed (if invalid: throw exception)

					Object methodResult = invoke();

					// TODO
					// Calling a method on a object can change its state
					// We therefore must update current tab
					// Tab selectedTab = userInterface.getTabs().getSelected();
					// if (selectedTab != null) {
					// selectedTab.onRefresh();
					// }
					callBack.process(userInterfaceContainer, methodInfo, methodOwner, methodParameter, methodResult);
				} catch (Throwable throwable) {
					TranslatableString title = methodInfo.getTitle(methodParameter);
					userInterface.showError(title, FAILED_TO_EXECUTE_MESSAGE, throwable);
				}
			}
		};
	}

	public Object invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = methodInfo.getMethod();
		if (methodInfo.hasParameter()) {
			return method.invoke(methodOwner, methodParameter);
		} else {
			return method.invoke(methodOwner);
		}
	}

}
