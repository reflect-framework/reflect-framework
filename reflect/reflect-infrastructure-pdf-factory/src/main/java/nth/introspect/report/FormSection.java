package nth.introspect.report;

import nth.introspect.layer4infrastructure.InfrastructureObject;

/**
 * @deprecated There no longer is a {@link ReportProvider}. You can create a {@link InfrastructureObject}
 * that could create E.G. a PdfReportFactory. These classes do not need to have
 * a superclass nor implement a an interface. The {@link ReportProvider} is therefore no
 * longer needed and needs to be removed where possible
 */

public class FormSection extends Section {

	private final Object domainObject;

	public FormSection(String name, Object domainObject) {
		super(name);
		this.domainObject = domainObject;
	}

	public Object getDomainObject() {
		return domainObject;
	}


}
