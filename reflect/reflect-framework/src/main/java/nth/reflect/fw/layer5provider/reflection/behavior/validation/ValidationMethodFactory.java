package nth.reflect.fw.layer5provider.reflection.behavior.validation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.validation.ValidationAnnotation;
import nth.reflect.fw.layer5provider.validation.ValidationViolations;

/**
 * The {@link UserInterfaceController} sometimes let’s the user edit an
 * {@link ActionMethod} parameter (e.g. a primitive type or a
 * {@link DomainObject}), depending on how the {@link ActionMethod} is
 * annotated, see {@link ExecutionMode}. The {@link UserInterfaceController}
 * then validates the edited {@link ActionMethod} parameter using the
 * {@link ValidationProvider} before the {@link ActionMethod} is invoked. The
 * {@link ValidationProvider} will use {@link ValidationAnnotation}s and
 * {@link ValidationMethod}s.
 * <p>
 * <h3>Validation annotations</h3>
 * <p>
 * {@insert ValidationAnnotation}
 * <p>
 * <h3>Validation methods</h3>
 * <p>
 * {@insert ValidationMethod}
 * </p>
 * 
 * @author nilsth
 *
 */
public class ValidationMethodFactory {

	/**
	 * 
	 * @param objectClass
	 * @return all methods that are {@link ValidationMethod}'s, assuming other
	 *         methods do not return {@link ValidationViolations} and method names
	 *         do not have a "Validation" suffix
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
