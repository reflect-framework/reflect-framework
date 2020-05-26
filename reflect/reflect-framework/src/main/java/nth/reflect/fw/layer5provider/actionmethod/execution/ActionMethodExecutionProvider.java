package nth.reflect.fw.layer5provider.actionmethod.execution;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public interface ActionMethodExecutionProvider extends Provider {
	/**
	 * This method is called from {@link ActionMethodInfo#execute(Object, Object)}
	 * by {@link #processActionMethod(Object, ActionMethodInfo, Object)} or from the
	 * {@link FormOkItem} linked to the OK button, via
	 * {@link ActionMethodInfo#execute(UserInterfaceContainer, Object, Object)} <br>
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

	public void execute(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter);
}
