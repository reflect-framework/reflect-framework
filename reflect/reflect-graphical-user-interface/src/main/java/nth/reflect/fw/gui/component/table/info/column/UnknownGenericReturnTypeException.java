package nth.reflect.fw.gui.component.table.info.column;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.gui.component.table.info.dataprovider.MethodReturnTypeNotSupportedException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class UnknownGenericReturnTypeException extends TranslatableException {

	private static final long serialVersionUID = -7508014122146381985L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodReturnTypeNotSupportedException.class.getCanonicalName() + ".message",
			"The generic return type of method: %s is unknown.");

	public UnknownGenericReturnTypeException(Method method) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)));
	}

}
