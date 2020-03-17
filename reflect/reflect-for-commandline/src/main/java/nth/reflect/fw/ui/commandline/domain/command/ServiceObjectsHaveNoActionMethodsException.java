package nth.reflect.fw.ui.commandline.domain.command;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ServiceObjectsHaveNoActionMethodsException extends TranslatableException {

	private static final long serialVersionUID = 1775903831361326458L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ServiceObjectsHaveNoActionMethodsException.class.getCanonicalName() + ".message",
			"None of the service objects have a visible action method.");

	public ServiceObjectsHaveNoActionMethodsException() {
		super(MESSAGE);
	}
}
