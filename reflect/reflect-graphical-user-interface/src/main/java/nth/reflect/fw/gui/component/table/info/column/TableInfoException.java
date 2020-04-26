package nth.reflect.fw.gui.component.table.info.column;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class TableInfoException extends TranslatableException {

	private static final long serialVersionUID = 2497559818590851587L;

	public TableInfoException(TranslatableString message) {
		super(message);
	}

	public TableInfoException(TranslatableString message, Throwable cause) {
		super(message, cause);
	}

}
