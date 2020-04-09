package nth.reflect.fw.gui;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class IllegalReflectUriException extends TranslatableException {

	private static final long serialVersionUID = -8899215681660490943L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			IllegalReflectUriException.class.getCanonicalName() + ".message",
			"Illegal Reflect URI. Format must be a standard URI like http://www.google.com or of format: "
					+ "Reflect:<service class package>.<service class name>.<service class method>");

	public IllegalReflectUriException(Throwable cause) {
		super(MESSAGE, cause);
	}

}
