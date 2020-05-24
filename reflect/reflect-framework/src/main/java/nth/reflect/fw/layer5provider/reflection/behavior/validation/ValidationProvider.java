package nth.reflect.fw.layer5provider.reflection.behavior.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;

/**
 * <p>
 * {@insert ValidationMethodFactory}
 * 
 * <h3>ValidationProvider implementation</h3>
 * <p>
 * There are many validation libraries and frameworks available. The
 * ReflectFramework complies with the JSR303 by using the
 * <a href="http://bval.apache.org/">Apache Bean Validator (BVal)</a> library,
 * combined with {@link ValidationMethod}s. You are free to implement or extend
 * your own implementation and register it to your {@link ReflectApplication} by
 * overriding the {@link ReflectApplication#getValidationProviderClass()} method
 * <p>
 * 
 * @author nilsth
 *
 */

public interface ValidationProvider extends Provider {

	public Set<ConstraintViolation<Object>> validate(Object domainObject);
}
