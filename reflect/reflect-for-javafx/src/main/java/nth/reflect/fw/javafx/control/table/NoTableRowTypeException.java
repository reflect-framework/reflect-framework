package nth.reflect.fw.javafx.control.table;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class NoTableRowTypeException extends TranslatableException {

	private static final long serialVersionUID = 5881475905598656994L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			NoTableRowTypeException.class.getComponentType() + ".message", "Expected items of type TableRows");

	public NoTableRowTypeException() {
		super(MESSAGE);
	}

}
