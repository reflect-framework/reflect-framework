package nth.reflect.fw.layer5provider.reflection.behavior.description;

import nth.reflect.fw.layer3domain.DomainObject;

@Description(englishDescription = AnnotatedDomainObject.ANNOTATED_CLASS_DESCRIPTION)
public class AnnotatedDomainObject extends DomainObject {

	static final String ANNOTATED_CLASS_DESCRIPTION = "Annotated Class Description";
	static final String ANNOTATED_ACTION_METHOD_DESCRIPTION = "Annotated Action Method Description";
	static final String ANNOTATED_PROPERTY_ACTION_METHOD_DESCRIPTION = "Annotated Property Action Method Description";
	static final String ANNOTATED_PROPERTY_DESCRIPTION = "Annotated Property Description";

	@Description(englishDescription = ANNOTATED_ACTION_METHOD_DESCRIPTION)
	@Override
	public void actionMethod(DomainObject domainObject) {
		super.actionMethod(domainObject);
	}

	@Description(englishDescription = ANNOTATED_PROPERTY_DESCRIPTION)
	@Override
	public Byte getMyByte() {
		return super.getMyByte();
	}
}
