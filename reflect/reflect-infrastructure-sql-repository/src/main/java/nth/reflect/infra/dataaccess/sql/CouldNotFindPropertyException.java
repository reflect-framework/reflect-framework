package nth.reflect.infra.dataaccess.sql;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotFindPropertyException extends TranslatableException {

	private static final long serialVersionUID = 7994004077722761350L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotFindPropertyException.class.getCanonicalName() + ".message",
			"Could not find property: %s in class: %s");

	public CouldNotFindPropertyException(String columnName, Class<?> domainClass) {
		super(MESSAGE.withParameters(columnName, domainClass.getCanonicalName()));
	}

}
