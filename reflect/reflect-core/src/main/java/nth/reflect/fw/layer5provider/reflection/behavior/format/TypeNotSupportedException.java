package nth.reflect.fw.layer5provider.reflection.behavior.format;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.NumericFormat;

public class TypeNotSupportedException extends ReflectTranslatableException {

	private static final long serialVersionUID = 2460695691202447353L;
	@Translatable
	private static final String TYPE_NOT_SUPPORTED_MESSAGE = "Type: %s, is not supported in class: %s";

	/**
	 * @deprecated Only used in {@link NumericFormat} which is to be depricated
	 * @param notSupportedType
	 * @param ownerClass
	 */
	public TypeNotSupportedException( Class<?> notSupportedType, Class<? > ownerClass) {
		super( TYPE_NOT_SUPPORTED_MESSAGE, notSupportedType, ownerClass.getCanonicalName());
	}


}
