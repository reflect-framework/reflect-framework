package nth.reflect.fw.gui.component.table.info;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TableValuesException extends TranslatableException {

	private static final long serialVersionUID = 7704084470210097611L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			TableValuesException.class.getCanonicalName() + ".message", "Could not get table values from: %s");

	public TableValuesException(ActionMethodInfo methodInfo, Exception e) {
		super(MESSAGE.withParameters(methodInfo.getCanonicalName()), e);
	}

}
