package nth.reflect.fw.layer5provider.actionmethodexecution.result;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;

public class ResultHandlerSerice {

	public static final String NO_RETURN_VALUE = "noReturnValue";

	public void noReturnValue() {

	}

	public static final String DOMAIN_OBJECT_RETURN_VALUE = "domainObjectReturnValue";

	public FullFeatureDomainObject domainObjectReturnValue() {
		return new FullFeatureDomainObject();
	}

}
