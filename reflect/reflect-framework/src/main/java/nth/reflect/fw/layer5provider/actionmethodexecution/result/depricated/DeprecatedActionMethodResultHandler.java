package nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * @deprecated All {@link UserInterfaceController}.show... methods need to be
 *             replaced with {@link ActionMethodResultHandler}s. This
 *             {@link ActionMethodResultHandler} meanwhile handles these
 *             methods.
 */
@Deprecated
public class DeprecatedActionMethodResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo actionMethodInfo) {
		ReflectApplication application = container.get(ReflectApplication.class);
		Class<? extends UserInterfaceController> userInterfaceClass = application.getUserInterfaceControllerClass();
		Method actionMethod = actionMethodInfo.getMethod();
		TypeInfo typeInfo = actionMethodInfo.getReturnTypeInfo();
		try {
			DeprecatedShowMethodFactory.create(userInterfaceClass, actionMethod, typeInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameter, Object methodResult) {
		Method actionMethod = actionMethodInfo.getMethod();
		TypeInfo typeInfo = actionMethodInfo.getReturnTypeInfo();
		UserInterfaceController userInterface = container.get(UserInterfaceController.class);
		Method showResultMethod = DeprecatedShowMethodFactory.create(userInterface.getClass(), actionMethod, typeInfo);
		try {
			if (actionMethodInfo.hasReturnValue()) {
				showResultMethod.invoke(userInterface, methodOwner, actionMethodInfo, methodParameter, methodResult);
			} else {
				showResultMethod.invoke(userInterface, methodOwner, actionMethodInfo, methodParameter);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
