package nth.introspect.layer5provider.reflection.behavior.validation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethods;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.validation.ValidationProvider;
import nth.introspect.layer5provider.validation.ValidationViolations;

/**
 * <p>
 * The {@link UserInterfaceController} sometimes let’s the user edit an
 * {@link DomainObject} before it is passed as an {@link ActionMethod} parameter
 * (depending on how the {@link ActionMethod} is annotated, see
 * {@link ExecutionMode}). The {@link UserInterfaceController} then validates
 * the edited {@link DomainObject} using the {@link ValidationProvider} before
 * the {@link ActionMethod} is called.The {@link ValidationProvider} will use
 * validation annotations and validation methods that are located in the
 * {@link DomainObject}s them selves to validate the {@link DomainObject}s.
 * </p>
 * 
 * <h3>Validation annotations</h3>
 * <p>
 * A {@link DomainObjectProperty}s can be validated by putting validation
 * annotations before the getter method of a property. The
 * {@link IntrospectFramework} uses the JSR303 complient Apache BVal library for
 * validation. Please read the BVal documentation on how to annotate the getter
 * methods of the properties.
 * </p>
 * <h3>Validation methods</h3>
 * <p>
 * {@insert ValidationMethod}
 * </p>
 *
 * 
 * 
 * 
 * 
 * @author nilsth
 *
 */
public class ValidationMethodFactory {

	/**
	 * 
	 * @param objectClass
	 * @return all methods that are {@link ValidationMethod}'s, assuming other
	 *         methods do not return {@link ValidationViolations} and method
	 *         names do not have a "Validation" suffix
	 */
	public static List<Method> create(Class<?> objectClass) {
		List<Method> validationMethods = new ArrayList<>();
		Method[] methods = objectClass.getDeclaredMethods();
		for (Method method : methods) {
			if (BehavioralMethods.VALIDATION.isValid(method)) {
				validationMethods.add(method);
			}
		}
		return validationMethods;
	}
}
