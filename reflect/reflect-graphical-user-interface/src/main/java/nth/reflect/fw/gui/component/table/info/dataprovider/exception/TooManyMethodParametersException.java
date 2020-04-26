package nth.reflect.fw.gui.component.table.info.dataprovider.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class TooManyMethodParametersException extends DataProviderException {
	private static final long serialVersionUID = 3180650761406319033L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodReturnTypeNotSupportedException.class.getCanonicalName() + ".message",
			"Method: %s has more than 1 parameter and is therefor unsuited to return table values.");

	public TooManyMethodParametersException(Method method) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)));
	}
}
