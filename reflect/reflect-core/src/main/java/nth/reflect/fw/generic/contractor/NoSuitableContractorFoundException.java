package nth.reflect.fw.generic.contractor;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class NoSuitableContractorFoundException extends TranslatableException {

	private static final long serialVersionUID = 2131022500996727068L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			NoSuitableContractorFoundException.class.getCanonicalName() + ".message",
			"%s: Could not find a suitable contractor for: %s");

	public NoSuitableContractorFoundException(DelegatingContractor delegatingContractor, Object makeInfo) {
		super(MESSAGE.withParameters(delegatingContractor.getClass().getSimpleName(), makeInfo));
	}

}
