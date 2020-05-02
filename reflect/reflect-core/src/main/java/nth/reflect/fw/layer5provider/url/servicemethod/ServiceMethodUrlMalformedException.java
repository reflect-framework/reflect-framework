package nth.reflect.fw.layer5provider.url.servicemethod;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ServiceMethodUrlMalformedException extends TranslatableException {

	private static final long serialVersionUID = -8899215681660490943L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ServiceMethodUrlMalformedException.class.getCanonicalName() + ".message", "Illegal Reflect URI. Format must be: "
					+ "%s:<service class package>.<service class name>.<service class method>");

	public ServiceMethodUrlMalformedException(Throwable cause) {
		super(MESSAGE.withParameters(ServiceMethodUrl.PROTOCOL), cause);
	}

}
