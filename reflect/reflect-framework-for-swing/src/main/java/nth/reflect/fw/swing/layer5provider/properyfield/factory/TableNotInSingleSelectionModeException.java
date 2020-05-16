package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class TableNotInSingleSelectionModeException extends TranslatableException {

	private static final long serialVersionUID = 3262296136838184052L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			TableNotInSingleSelectionModeException.class.getCanonicalName() + ".message",
			"Table must be in single selection mode!!!");

	public TableNotInSingleSelectionModeException() {
		super(MESSAGE);
	}

}
