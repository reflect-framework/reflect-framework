package nth.introspect.provider.validation;

import nth.introspect.provider.domain.info.property.PropertyInfo;

public class DefaultValidationProvider implements ValidationProvider {

	@Override
	public void validate(PropertyInfo property, Object domainObject, Object propertyValue) throws ValidationException {
		//default implementation does not throw any ValidationExceptions
	}
}
