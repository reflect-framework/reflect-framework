package nth.reflect.fw.gui.proider.actionmethodexecution.result;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;

public class ResultHandlerSerice {

	public static final String NO_RETURN_VALUE = "noReturnValue";

	public void noReturnValue() {

	}

	public static final String DOMAIN_OBJECT_RETURN_VALUE = "domainObjectReturnValue";

	public AllFeatureDomainObject domainObjectReturnValue() {
		return new AllFeatureDomainObject();
	}

}
