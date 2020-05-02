package nth.reflect.fw.layer5provider.url.exception;

import java.net.URL;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotOpenUrlException extends TranslatableException {

	private static final long serialVersionUID = 8051375486181461426L;
	public static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotOpenUrlException.class.getCanonicalName() + ".message", "Could not open URL: %s");

	public CouldNotOpenUrlException(URL url, Throwable e) {
		super(MESSAGE.withParameters(url.toString()), e);
	}
}
