package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MethodNotFoundException extends TranslatableException {

	private static final long serialVersionUID = 4523194203494218611L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodNotFoundException.class.getCanonicalName() + ".message",
			"Could not find method %s in " + DomainObject.class.getSimpleName());

	public MethodNotFoundException(String domainObjectGetterMethod) {
		super(MESSAGE.withParameters(domainObjectGetterMethod));
	}

}
