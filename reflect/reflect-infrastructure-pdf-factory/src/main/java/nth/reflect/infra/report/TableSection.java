package nth.reflect.infra.report;

import java.util.Collection;

import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
/**
 * @deprecated There no longer is a {@link ReportProvider}. You can create a {@link InfrastructureObject}
 * that could create E.G. a PdfReportFactory. These classes do not need to have
 * a superclass nor implement a an interface. The {@link ReportProvider} is therefore no
 * longer needed and needs to be removed where possible
 */
public class TableSection extends Section {


	private final Collection<?> objects;
	private final Class<?> objectClass;

	public TableSection(String name, Collection<?> objects, Class<?> objectClass) {
		super(name);
		this.objects = objects;
		this.objectClass = objectClass;
	}

	public Collection<?> getObjects() {
		return objects;
	}

	public Class<?> getDomainObjectClass() {
		return objectClass;
	}

	

}
