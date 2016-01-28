package nth.introspect.layer5provider.reflection.behavior.executionmode;

import java.lang.reflect.Method;

import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * Gets the {@link ExecutionModeType} from the {@link ExecutionModeFactory}
 * annotation and determines the {@link ExecutionModeType} for the
 * {@link ActionMethod}
 * </p>
 * 
 * @author nilsth
 *
 */
public class ExecutionModeFactory {

	public static ExecutionModeType create(Method method) {
		ExecutionMode executionModeAnnotation = method
				.getAnnotation(ExecutionMode.class);
		if (executionModeAnnotation == null) {
			return defaultExecutionMode(method);
		} else if (methodHasNoParameters(method)) {
			return executionModeForMethodWithoutParameters(
					executionModeAnnotation);
		} else {
			return executionModeAnnotation.mode();
		}
	}

	private static ExecutionModeType defaultExecutionMode(Method method) {
		if (methodHasNoParameters(method)) {
			return ExecutionModeType.EXECUTE_METHOD_DIRECTLY;
		} else {
			return ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL;
		}
	}

	private static ExecutionModeType executionModeForMethodWithoutParameters(
			ExecutionMode executionModeAnnotation) {
		switch (executionModeAnnotation.mode()) {
		case EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL:
			return ExecutionModeType.EXECUTE_METHOD_DIRECTLY;
		case EXECUTE_METHOD_AFTER_CONFORMATION:
			return ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION;
		default:
			return ExecutionModeType.EXECUTE_METHOD_DIRECTLY;
		}
	}

	private static boolean methodHasNoParameters(Method method) {
		return method.getParameterTypes().length == 0;
	}
}
