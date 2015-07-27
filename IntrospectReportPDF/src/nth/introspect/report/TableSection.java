package nth.introspect.report;

import java.util.Collection;

import nth.introspect.layer4infrastructure.InfrastructureObject;
/**
 * @deprecated There no longer is a {@link ReportProvider}. You can create a {@link InfrastructureObject}
 * that could create E.G. a PdfReportFactory. These classes do not need to have
 * a superclass nor implement a an interface. The {@link ReportProvider} is therefore no
 * longer needed and needs to be removed where possible
 */
public class TableSection extends Section {


	private final Collection<?> introspectedObjects;
	private final Class<?> introspectedObjectType;

	public TableSection(String name, Collection<?> introspectedObjects, Class<?> introspectedObjectType) {
		super(name);
		this.introspectedObjects = introspectedObjects;
		this.introspectedObjectType = introspectedObjectType;
	}

	public Collection<?> getIntrospectedObjects() {
		return introspectedObjects;
	}

	public Class<?> getIntrospectedObjectType() {
		return introspectedObjectType;
	}

	

}
