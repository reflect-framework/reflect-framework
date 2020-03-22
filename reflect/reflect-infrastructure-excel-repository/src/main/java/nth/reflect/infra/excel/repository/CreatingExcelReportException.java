package nth.reflect.infra.excel.repository;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CreatingExcelReportException extends TranslatableException {

	private static final long serialVersionUID = -448250008119601414L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CreatingExcelReportException.class.getCanonicalName() + ".message", "Error creating excel report");

	public CreatingExcelReportException(Exception e) {
		super(MESSAGE, e);
	}

}
