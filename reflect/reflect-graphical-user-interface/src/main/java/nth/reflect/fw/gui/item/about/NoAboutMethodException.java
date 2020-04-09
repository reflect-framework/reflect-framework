package nth.reflect.fw.gui.item.about;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.version.VersionProvider;

public class NoAboutMethodException extends TranslatableException {

	private static final long serialVersionUID = 178234217838110967L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			NoAboutMethodException.class.getCanonicalName() + ".message", "Could not find %s.%s method.");

	public NoAboutMethodException(VersionProvider versionProvider, String aboutMethodName) {
		super(MESSAGE.withParameters(versionProvider.getClass().getCanonicalName(), aboutMethodName));
	}

}
