package nth.reflect.fw.gui.component.table.info.dataprovider.exception;

import nth.reflect.fw.gui.component.table.info.column.TableInfoException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public abstract class DataProviderException extends TableInfoException {

	private static final long serialVersionUID = -2968650274901471393L;

	public DataProviderException(TranslatableString message) {
		super(message);
	}

	public DataProviderException(TranslatableString message, Throwable throwable) {
		super(message, throwable);
	}

}
