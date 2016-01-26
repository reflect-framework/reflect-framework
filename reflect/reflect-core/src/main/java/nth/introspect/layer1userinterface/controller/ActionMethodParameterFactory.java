package nth.introspect.layer1userinterface.controller;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

public class ActionMethodParameterFactory {
	public static Object createIfNeeded(
			UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo methodInfo, Object methodParameter) {
		if (needToCreateActionMethodParameter(methodInfo, methodParameter)) {
			methodParameter = createParameter(userInterfaceContainer,
					methodOwner, methodInfo);
		}
		return methodParameter;
	}

	private static boolean needToCreateActionMethodParameter(
			ActionMethodInfo actionMethodInfo, Object actionMethodParameterValue) {
		boolean methodHasParameter = actionMethodInfo.getParameterType()
				.getTypeCategory() != TypeCategory.NONE;
		return methodHasParameter && actionMethodParameterValue == null;
	}

	private static Object createParameter(
			UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo methodInfo) {
		if (methodInfo.hasParameterFactory()) {
			try {
				Object methodParameter = methodInfo
						.createMethodParameter(methodOwner);
				return methodParameter;
			} catch (InstantiationException | IllegalAccessException e) {
				throw new ActionMethodParameterCanNotBeCreated(methodInfo, e);
			}

		} else {
			Class<?> parameterType = methodInfo.getParameterType().getType();
			if (TypeUtil.isDomainType(parameterType)) {
				Object methodParameter = userInterfaceContainer
						.get(parameterType);
				return methodParameter;
			} else {
				throw new ActionMethodParameterCanNotBeCreated(methodInfo);
			}
		}

	}
}
