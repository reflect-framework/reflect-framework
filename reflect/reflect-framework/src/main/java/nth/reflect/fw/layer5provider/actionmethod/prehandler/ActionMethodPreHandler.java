package nth.reflect.fw.layer5provider.actionmethod.prehandler;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * An {@link ActionMethodPreHandler} does some actions before an
 * {@link ActionMethod} is invoked by a {@link ActionMethodInvoker}, e.g. see
 * following paragraphs:
 * 
 * <h3>TODO</h3> {@insert ExecuteDirectly}
 * 
 * <h3>Custom ActionmethodPreHandler</h3> You can add, remove or change the
 * order of the {@link ActionMethodPreHandler}'s by overriding the
 * {@link ReflectApplication#getActionMethodPreHandlers}.
 * 
 * @author nilsth
 *
 */
public interface ActionMethodPreHandler {

	public boolean canProcess(ActionMethodInfo methodInfo);

	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception;

}
