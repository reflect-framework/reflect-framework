package nth.reflect.infra.generic.xml;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CollectionTypeNotSupportedException extends TranslatableException {

	private static final long serialVersionUID = 4154357574902685970L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CollectionTypeNotSupportedException.class.getCanonicalName() + ".message",
			"A collection of type: %s is not supported yet");

	public CollectionTypeNotSupportedException(Class<?> propertyType) {
		super(MESSAGE.withParameters(propertyType.getCanonicalName()));
	}

}
