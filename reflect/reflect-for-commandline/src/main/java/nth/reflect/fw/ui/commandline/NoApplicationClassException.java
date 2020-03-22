package nth.reflect.fw.ui.commandline;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class NoApplicationClassException extends TranslatableException {

	private static final long serialVersionUID = -6822576978928397679L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			NoApplicationClassException.class.getCanonicalName() + ".message", "Unable to determine Application class");

	public NoApplicationClassException() {
		super(MESSAGE);
	}

}
