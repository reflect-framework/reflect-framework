package nth.reflect.fw.gui.component.table.info.dataprovider.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class GettingTableValuesException extends DataProviderException {

	private static final long serialVersionUID = 7704084470210097611L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			GettingTableValuesException.class.getCanonicalName() + ".message", "Could not get table values from: %s");

	public GettingTableValuesException(Method method, Exception e) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)), e);
	}

}
