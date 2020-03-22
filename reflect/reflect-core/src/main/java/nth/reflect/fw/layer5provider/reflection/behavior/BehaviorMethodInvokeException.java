package nth.reflect.fw.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class BehaviorMethodInvokeException extends TranslatableException {

	private static final long serialVersionUID = -8447500654942721817L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			BehaviorMethodInvokeException.class.getCanonicalName() + ".message",
			"Error invoking behavioral method: %s.%s");

	public BehaviorMethodInvokeException(Method behavioralMethod, Exception exception) {
		super(MESSAGE.withParameters(behavioralMethod.getDeclaringClass().getCanonicalName(),
				behavioralMethod.getName()));
	}

}
