package nth.introspect.layer5provider.report;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer4infrastructure.InfrastructureObject;
/**
 * @deprecated There no longer is a {@link ReportProvider}. You can create a {@link InfrastructureObject}
 * that could create E.G. a PdfReportFactory. These classes do not need to have
 * a superclass nor implement a an interface. The {@link ReportProvider} is therefore no
 * longer needed and needs to be removed where possible
 */

public class Report {

	private String reportName;
	private String fileName;
	private List<Section> sections;

	public Report(String reportName, String fileName) {
		this.reportName = reportName;
		this.fileName = fileName;
		this.sections = new ArrayList<Section>();
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @deprecated filename should come from the reportName combined with the extension given by the report provider
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Section> getSections() {
		return sections;
	}

	
	
	
}
