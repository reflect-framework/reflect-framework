package nth.introspect.provider.validation;

import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.property.PropertyInfo;

//use a JSR-303 implementation where possible! 
public interface ValidationProvider extends Provider{
	public void validate(PropertyInfo property, Object domainObject, Object propertyValue) throws ValidationException;
}
