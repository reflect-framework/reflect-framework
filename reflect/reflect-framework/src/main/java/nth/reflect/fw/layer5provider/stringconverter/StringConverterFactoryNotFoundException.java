package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class StringConverterFactoryNotFoundException extends TranslatableException {

	private static final long serialVersionUID = -4206493834374235530L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			StringConverterFactoryNotFoundException.class.getCanonicalName() + ".message",
			"%s: Could not found a %s for: %s.");

	public StringConverterFactoryNotFoundException(TypeInfo typeInfo) {
		super(MESSAGE
				.withParameters(StringConverterProvider.class.getSimpleName(),
						StringConverterFactory.class.getSimpleName(), typeInfo.toString()));
	}
}
