package nth.reflect.infra.generic.xml;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MustHaveReflectFrameworkRootElementException extends TranslatableException {

	private static final long serialVersionUID = 5857538781885517225L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MustHaveReflectFrameworkRootElementException.class.getCanonicalName() + ".message",
			"The XML document must have a root element <%s>");

	public MustHaveReflectFrameworkRootElementException(String rootElementName) {
		super(MESSAGE.withParameters(rootElementName));
	}

}
