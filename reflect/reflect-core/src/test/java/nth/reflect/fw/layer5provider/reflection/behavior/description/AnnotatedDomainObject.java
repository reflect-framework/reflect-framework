package nth.reflect.fw.layer5provider.reflection.behavior.description;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;

@DisplayName(defaultEnglish = AnnotatedDomainObject.ANNOTATED_CLASS_DISPLAY_NAME)
@Description(defaultEnglish = AnnotatedDomainObject.ANNOTATED_CLASS_DESCRIPTION)
public class AnnotatedDomainObject extends DomainObject {

	public static final String ANNOTATED_CLASS_DISPLAY_NAME = "Annotated Class Display Name";
	public static final String ANNOTATED_CLASS_DESCRIPTION = "Annotated Class Description";
	public static final String ANNOTATED_ACTION_METHOD_DESCRIPTION = "Annotated Action Method Description";
	public static final String ANNOTATED_ACTION_METHOD_DISPLAY_NAME = "Annotated Action Method Display Name";
	public static final String ANNOTATED_PROPERTY_DISPLAY_NAME = "Annotated Property Display Name";
	public static final String ANNOTATED_PROPERTY_DESCRIPTION = "Annotated Property Description";
	public static final String ANNOTATED_PROPERTY_ACTION_METHOD_DISPLAY_NAME = "Annotated Property Action Method Display Name";
	public static final String ANNOTATED_PROPERTY_ACTION_METHOD_DESCRIPTION = "Annotated Property Action Method Description";

	@Description(defaultEnglish = ANNOTATED_ACTION_METHOD_DESCRIPTION)
	@DisplayName(defaultEnglish = ANNOTATED_ACTION_METHOD_DISPLAY_NAME)
	@Override
	public void actionMethod(DomainObject domainObject) {
		super.actionMethod(domainObject);
	}

	@DisplayName(defaultEnglish = ANNOTATED_PROPERTY_DISPLAY_NAME)
	@Description(defaultEnglish = ANNOTATED_PROPERTY_DESCRIPTION)
	@Override
	public Byte getMyByte() {
		return super.getMyByte();
	}

	@DisplayName(defaultEnglish = ANNOTATED_PROPERTY_ACTION_METHOD_DISPLAY_NAME)
	@Description(defaultEnglish = ANNOTATED_PROPERTY_ACTION_METHOD_DESCRIPTION)
	@Override
	public void myDomainObjectListAdd(DomainObject domainObject) {
		super.myDomainObjectListAdd(domainObject);
	}
}
