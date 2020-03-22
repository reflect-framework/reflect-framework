package nth.reflect.fw.ui.swing;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class NoApplicationSubClassException extends TranslatableException {

	private static final long serialVersionUID = 714654701676566601L;

	private static TranslatableString MESSAGE = new TranslatableString(
			NoApplicationSubClassException.class.getCanonicalName(), "%s is not a subclass of %s");

	public NoApplicationSubClassException(Class applicationClass) {
		super(MESSAGE.withParameters(applicationClass.getCanonicalName(),
				ReflectApplicationForSwing.class.getCanonicalName()));
	}

}
