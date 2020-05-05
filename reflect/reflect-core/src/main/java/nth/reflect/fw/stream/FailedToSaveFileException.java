package nth.reflect.fw.stream;

import java.io.File;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class FailedToSaveFileException extends TranslatableException {

	private static final long serialVersionUID = 5496462986664441578L;
	public static final TranslatableString MESSAGE = new TranslatableString(
			FailedToSaveFileException.class.getCanonicalName() + ".message", "Failed to save file:  %s");

	public FailedToSaveFileException(File file, Exception e) {
		super(MESSAGE.withParameters(file.getAbsolutePath()), e);
	}

}
