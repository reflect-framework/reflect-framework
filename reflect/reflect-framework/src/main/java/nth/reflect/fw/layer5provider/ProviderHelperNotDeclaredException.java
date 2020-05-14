package nth.reflect.fw.layer5provider;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ProviderHelperNotDeclaredException extends TranslatableException {

	private static final long serialVersionUID = -8235951268136959328L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ProviderHelperNotDeclaredException.class.getCanonicalName() + ".message",
			"Error trying to get at least one %s from method: %s");

	public ProviderHelperNotDeclaredException(Class<?> providerHelperClass, String canonicalMethodName) {
		super(MESSAGE.withParameters(providerHelperClass.getSimpleName(), canonicalMethodName));
	}

}
