package nth.introspect.provider.report;

import java.util.ArrayList;
import java.util.List;


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
