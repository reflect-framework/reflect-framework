package nth.introspect.layer5provider.report;

import nth.introspect.layer4infrastructure.InfrastructureObject;

/**
 * @deprecated There no longer is a {@link ReportProvider}. You can create a {@link InfrastructureObject}
 * that could create E.G. a PdfReportFactory. These classes do not need to have
 * a superclass nor implement a an interface. The {@link ReportProvider} is therefore no
 * longer needed and needs to be removed where possible
 */

public class FormSection extends Section {

	private final Object introspectedObject;

	public FormSection(String name, Object introspectedObject) {
		super(name);
		this.introspectedObject = introspectedObject;
	}

	public Object getIntrospectedObject() {
		return introspectedObject;
	}


}
