package nth.introspect.layer1userinterface.controller.processmethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.MethodParameterTypeNotSupported;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

/**
 * A {@link ProcessMethod} is a method in the {@link UserInterfaceController}
 * (or in one of its sub classes) that helps to execute an {@link ActionMethod}.
 * The {@link UserInterfaceController} supports different {@link ActionMethod}
 * parameter types or {@link ActionMethod} result types with
 * {@link ProcessMethod}s that have different parameter signutures.
 * 
 * @author nilsth
 *
 */
public abstract class ProcessMethod {

	protected abstract Method getProcessMethod();

	protected abstract Class<?>[] getProcessMethodParameterTypes();

	protected abstract Object[] getProcessMethodParameterValues();

	protected abstract UserInterfaceController getUserInterfaceController();

	public void invoke() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object[] parameterValues = getProcessMethodParameterValues();
		Method processMethod = getProcessMethod();
		UserInterfaceController userInterfaceController = getUserInterfaceController();
		processMethod.invoke(userInterfaceController, parameterValues);
	}

}
