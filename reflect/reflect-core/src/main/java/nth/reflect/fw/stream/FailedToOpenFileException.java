package nth.reflect.fw.stream;

import java.io.File;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class FailedToOpenFileException extends TranslatableException {

	private static final long serialVersionUID = 1429512330653343236L;
	public static final TranslatableString MESSAGE = new TranslatableString(
			FailedToOpenFileException.class.getCanonicalName() + ".message", "Failed to open file: %s");

	public FailedToOpenFileException(File file, Exception e) {
		super(MESSAGE.withParameters(file.getAbsolutePath()), e);
	}

}
