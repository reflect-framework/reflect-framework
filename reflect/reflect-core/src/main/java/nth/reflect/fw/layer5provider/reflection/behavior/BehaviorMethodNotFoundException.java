package nth.reflect.fw.layer5provider.reflection.behavior;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class BehaviorMethodNotFoundException extends TranslatableException {

	private static final long serialVersionUID = -8447500654942721817L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			BehaviorMethodInvokeException.class.getCanonicalName() + ".message",
			"Behavioral method: %s could not be found.");

	public BehaviorMethodNotFoundException(String behavioralMethodName) {
		super(MESSAGE.withParameters(behavioralMethodName));
	}

}
