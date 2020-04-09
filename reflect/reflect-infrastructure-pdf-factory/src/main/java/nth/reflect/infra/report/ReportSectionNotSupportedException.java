package nth.reflect.infra.report;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ReportSectionNotSupportedException extends TranslatableException {

	private static final long serialVersionUID = 5160580072391558570L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ReportSectionNotSupportedException.class.getCanonicalName() + ".message",
			"Report section: %s  is not supported for: %s");;

	public ReportSectionNotSupportedException(Section section, Object pdfReportFactory) {
		super(MESSAGE.withParameters(section.getClass().getCanonicalName(),
				pdfReportFactory.getClass().getCanonicalName()));
	}

}
