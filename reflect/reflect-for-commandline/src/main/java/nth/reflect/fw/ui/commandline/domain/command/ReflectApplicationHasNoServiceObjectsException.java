package nth.reflect.fw.ui.commandline.domain.command;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ReflectApplicationHasNoServiceObjectsException extends ReflectTranslatableException {

	private static final long serialVersionUID = -5808138348441149153L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ReflectApplicationHasNoServiceObjectsException.class.getCanonicalName() + ".message",
			"The Reflect Application does not have any service objects");

	public ReflectApplicationHasNoServiceObjectsException() {
		super(MESSAGE);
	}
}
