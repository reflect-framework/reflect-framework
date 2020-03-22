package nth.reflect.infra.generic.xml;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class TypeNotSupportedException extends TranslatableException {

	private static final long serialVersionUID = 3572540902740697737L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			TypeNotPresentException.class.getCanonicalName() + ".message", "Type: %s is not supported in: %s");

	public TypeNotSupportedException(Class<?> type) {
		super(MESSAGE.withParameters(type.getCanonicalName(), XmlConverter.class.getCanonicalName()));
	}

}
