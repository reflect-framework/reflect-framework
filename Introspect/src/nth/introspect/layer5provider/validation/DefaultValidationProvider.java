package nth.introspect.layer5provider.validation;

import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class DefaultValidationProvider implements ValidationProvider {

	@Override
	public ValidationResult validate(PropertyInfo property, Object domainObject, Object propertyValue)  {
		return new ValidationResult();
	}
}
