package nth.introspect.layer1userinterface.controller;

import nth.introspect.generic.exception.IntrospectException;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionMethodParameterCanNotBeCreated extends IntrospectException {

	private static final long serialVersionUID = -3742297262098383762L;

	public ActionMethodParameterCanNotBeCreated(ActionMethodInfo methodInfo) {
		super(createMessage(methodInfo));
	}

	public ActionMethodParameterCanNotBeCreated(ActionMethodInfo methodInfo,
			ReflectiveOperationException e) {
		super(createMessage(methodInfo), e);
	}

	private static String createMessage(ActionMethodInfo methodInfo) {
		String message="Can not create a new instance of method paraterer type: %s of action method: %s";
		String parameterType=methodInfo.getParameterType().getType().getCanonicalName();
		String methodName=methodInfo.getCanonicalName();
		return String.format(message, parameterType, methodName);
	}


}
