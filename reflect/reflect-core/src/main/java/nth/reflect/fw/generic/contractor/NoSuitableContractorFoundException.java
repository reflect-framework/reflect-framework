package nth.reflect.fw.generic.contractor;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;

public class NoSuitableContractorFoundException extends ReflectTranslatableException {

	private static final long serialVersionUID = 2131022500996727068L;
	private static final String MESSAGE = "%s: Could not find a suitable contractor for: %s";

	public NoSuitableContractorFoundException(DelegatingContractor delegatingContractor, Object makeInfo) {
		super(MESSAGE, delegatingContractor.getClass().getSimpleName(), makeInfo);
	}

}
