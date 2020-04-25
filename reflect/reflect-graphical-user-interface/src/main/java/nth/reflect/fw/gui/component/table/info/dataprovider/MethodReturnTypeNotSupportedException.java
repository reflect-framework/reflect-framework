package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MethodReturnTypeNotSupportedException extends TranslatableException {

	private static final long serialVersionUID = -9040797962147978326L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodReturnTypeNotSupportedException.class.getCanonicalName() + ".message",
			"Method return type: %s is not supported is not supported as table values.");

	public MethodReturnTypeNotSupportedException(Method method) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)));
	}
}
