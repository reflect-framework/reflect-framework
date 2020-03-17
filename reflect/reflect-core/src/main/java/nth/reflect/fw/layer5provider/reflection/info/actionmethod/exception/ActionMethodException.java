package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionMethodException extends TranslatableException {

	private static final long serialVersionUID = -7423921399017493023L;

	public ActionMethodException(TranslatableString message) {
		super(message);
	}

	public ActionMethodException(TranslatableString message, Method method) {
		super(message.withParameters(ActionMethodInfo.createCanonicalName(method)));
	}

}
