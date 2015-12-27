package nth.introspect.layer5provider.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * The {@link UserInterfaceController} sometimes let’s the user edit an
 * {@link DomainObject} before it is passed as an {@link ActionMethod} parameter
 * (depending on how the ActionMethod is annotated, see {@link ExecutionMode} ).
 * The {@link UserInterfaceController} then validates the edited
 * {@link DomainObject} using the {@link ValidationProvider} before the
 * {@link ActionMethod} is called.The {@link ValidationProvider} will use
 * ValidationAnnotationsTODO_LINK and ValidationMethods_TODO_LINK that are
 * located in the {@link DomainObject}s them selves so that they can be
 * validated.
 * </p>
 * 
 * <h3>ValidationProvider implementation</h3>
 * <p>
 * There are many validation libraries and frameworks available. The
 * IntrospectFramework complies with the JSR303 by using the <a href="http://bval.apache.org/">Apache Bean Validator (BVal)</a> library,
 * combined with {@link ValidationMethod}s. You are free to implement or extend
 * your own implementation and register it to your {@link IntrospectApplication} by
 * overriding the {@link IntrospectApplication#getValidationProviderClass()} method
 * </p>
 * 
 * @author nilsth
 *
 */

public interface ValidationProvider extends Provider {

	public Set<ConstraintViolation<Object>> validate(Object domainObject); 
}
