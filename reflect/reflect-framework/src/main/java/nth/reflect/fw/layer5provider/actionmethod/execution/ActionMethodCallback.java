package nth.reflect.fw.layer5provider.actionmethod.execution;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * A method that is being called by a ActionMethodInvoker when a result came
 * back from invoking an {@link ActionMethod}. This can be used to show new
 * {@link ActionMethod} results, e.g. show or update a read only form, table
 * 
 * @author nilsth
 *
 */
public interface ActionMethodCallback {

	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult);
}