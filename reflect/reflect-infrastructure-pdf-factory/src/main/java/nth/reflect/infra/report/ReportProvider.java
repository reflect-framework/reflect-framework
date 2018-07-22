package nth.reflect.infra.report;

import java.io.ByteArrayOutputStream;

import nth.reflect.fw.layer4infrastructure.InfrastructureObject;

/**
 * @deprecated There no longer is a {@link ReportProvider}. You can create a {@link InfrastructureObject}
 * that could create E.G. a PdfReportFactory. These classes do not need to have
 * a superclass nor implement a an interface. The {@link ReportProvider} is therefore no
 * longer needed and needs to be removed where possible
 */
public abstract class ReportProvider<T> {

	public ByteArrayOutputStream createReport(Report report) {
		T document = createDocument(report);
		for (Section section : report.getSections()) {
			if (section instanceof TableSection) {
				TableSection tableSection = (TableSection) section;
				addTableSection(document, report, tableSection);
			} else if (section instanceof FormSection) {
				FormSection formSection = (FormSection) section;
				addFormSection(document, report, formSection);
			} else {
				throw new RuntimeException("Report section "
						+ section.getClass().getCanonicalName()
						+ " is not supported for "
						+ this.getClass().getCanonicalName());
			}
		}
		ByteArrayOutputStream outputStream = createOutputStream(document,
				report);
		return outputStream;
	}

	public abstract T createDocument(Report report);

	public abstract void addFormSection(T document, Report report,
			FormSection formSection);

	public abstract void addTableSection(T document, Report report,
			TableSection tableSection);

	public abstract ByteArrayOutputStream createOutputStream(T document,
			Report report);
}
