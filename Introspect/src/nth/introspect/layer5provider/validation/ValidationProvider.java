package nth.introspect.layer5provider.validation;

import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

//use a JSR-303 implementation where possible! 
public interface ValidationProvider extends Provider{
	public void validate(PropertyInfo property, Object domainObject, Object propertyValue) throws ValidationException;
}
