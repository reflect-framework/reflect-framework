package nth.reflect.fw.layer5provider.reflection.info.property.exception;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotReadPropertyValue extends TranslatableException {

	private static final long serialVersionUID = -9173250745057196590L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotReadPropertyValue.class.getCanonicalName() + ".message", "Could not read value of property: %s");

	public CouldNotReadPropertyValue(String canonicalName, Exception e) {
		super(MESSAGE.withParameters(canonicalName), e);
	}

}
