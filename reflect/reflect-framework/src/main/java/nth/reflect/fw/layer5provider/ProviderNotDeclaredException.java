package nth.reflect.fw.layer5provider;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ProviderNotDeclaredException extends TranslatableException {

	private static final long serialVersionUID = -8235951268136959328L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ProviderNotDeclaredException.class.getCanonicalName() + ".message",
			"Error trying to get a provider from method: %s");

	public ProviderNotDeclaredException(Method applicationMethodToGetProvider, Exception exception) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(applicationMethodToGetProvider)), exception);
	}

	public ProviderNotDeclaredException(Method applicationMethodToGetProvider) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(applicationMethodToGetProvider)));
	}

}
