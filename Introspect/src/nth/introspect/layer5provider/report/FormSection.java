package nth.introspect.layer5provider.report;


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
