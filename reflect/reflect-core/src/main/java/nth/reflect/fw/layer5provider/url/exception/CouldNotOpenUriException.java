package nth.reflect.fw.layer5provider.url.exception;

import java.net.URI;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotOpenUriException extends TranslatableException {

	private static final long serialVersionUID = 8051375486181461426L;
	public static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotOpenUriException.class.getCanonicalName() + ".message", "Could not open URI: %s");

	public CouldNotOpenUriException(URI uri, Throwable e) {
		super(MESSAGE.withParameters(uri.toString()), e);
	}
}
