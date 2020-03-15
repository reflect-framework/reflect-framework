package nth.reflect.fw.gui.component.table.info;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class MethodReturnTypeNotSupportedException extends ReflectTranslatableException {

	private static final long serialVersionUID = -9040797962147978326L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodReturnTypeNotSupportedException.class.getCanonicalName() + ".message",
			"Method return type: %s is not supported is not supported as table values.");

	public MethodReturnTypeNotSupportedException(TypeInfo typeInfo) {
		super(MESSAGE.withParameters(typeInfo));
	}
}
