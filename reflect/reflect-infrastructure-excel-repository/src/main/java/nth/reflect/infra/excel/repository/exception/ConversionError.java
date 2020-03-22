package nth.reflect.infra.excel.repository.exception;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public abstract class ConversionError extends TranslatableException {

	private static final long serialVersionUID = -8170096916385373543L;

	public ConversionError(TranslatableString message) {
		super(message);
	}

	public ConversionError(TranslatableString message, Throwable cause) {
		super(message, cause);
	}

}
