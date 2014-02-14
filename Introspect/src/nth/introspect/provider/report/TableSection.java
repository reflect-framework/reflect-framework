package nth.introspect.provider.report;

import java.util.Collection;

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
