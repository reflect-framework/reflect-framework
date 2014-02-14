package nth.introspect.provider.report;

import java.io.ByteArrayOutputStream;

import nth.introspect.provider.Provider;

public abstract class ReportProvider<T> implements Provider {

	public ByteArrayOutputStream createReport(Report report) {
		T document=createDocument(report);
		for (Section section: report.getSections()) {
			if (section instanceof TableSection) {
				TableSection tableSection = (TableSection) section;
				addTableSection(document, report, tableSection);
			} else if (section instanceof FormSection) {
				FormSection formSection = (FormSection) section;
				addFormSection(document, report, formSection);
			} else {
				throw new RuntimeException("Report section " + section.getClass().getCanonicalName() + " is not supported for " + this.getClass().getCanonicalName());
			}
		}
		ByteArrayOutputStream outputStream=createOutputStream(document, report);
		return outputStream;
	}


	public abstract T createDocument(Report report) ;

	public abstract void addFormSection(T document, Report report, FormSection formSection);
	
	public abstract void addTableSection(T document, Report report, TableSection tableSection);

	public abstract ByteArrayOutputStream createOutputStream(T document, Report report);
}
