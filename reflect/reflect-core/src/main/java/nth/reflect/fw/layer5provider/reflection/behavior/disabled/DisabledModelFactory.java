package nth.reflect.fw.layer5provider.reflection.behavior.disabled;

import java.lang.reflect.Method;
import java.util.Optional;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * A {@link DomainObjectProperty} or {@link ActionMethod} is enabled on the user
 * interface by default, but in some cases you may want to disable them. If
 * something is disabled they are grayed out on the user interface. Disabled
 * properties can not be edited (read only) and disabled {@link ActionMethod}s
 * can not be invoked.
 * </p>
 * <p>
 * You may want to disable items when:
 * <ul>
 * <li>Not all properties need to be editable on the user interface but the code
 * might need a setter method to set the {@link DomainObjectProperty}value when
 * the {@link DomainObject} is created from the database</li>
 * <li>Depending on the state of a {@link DomainObject} you do not want the user
 * to edit certain {@link DomainObjectProperty}</li>
 * <li>Depending on the state of a {@link DomainObject} you do not want a user
 * to invoke an {@link ActionMethod} (e.g. prevent invoking {@link ActionMethod}
 * “submit” once the {@link DomainObject} already is submitted)</li>
 * <li>Because a user is not authorized to edit a {@link DomainObjectProperty}
 * Value</li>
 * </ul>
 * </p>
 * <p>
 * Important note: If an user is not authorized to change a
 * {@link DomainObjectProperty} or call an {@link ActionMethod} it is best to
 * hide the method or property instead of disabling it. In general you do not
 * want to confuse users (clutter the user interface) with options that they are
 * not allowed to use anyway. Disabled {@link ActionMethod}s
 * <a href="https://axesslab.com/disabled-buttons-suck/">have a bad impact on
 * usability</a>.
 * </p>
 * 
 * <h3>Disabled Annotation</h3>
 * <p>
 * {@insert Disabled}
 * </p>
 * 
 * <h3>Disabled Method</h3>
 * <p>
 * {@insert DisabledMethod}
 * </p>
 * 
 * <h3>Disabled Annotation and Disabled Method</h3>
 * <p>
 * {@insert DisabledOrModel}
 * </p>
 * 
 * @author nilsth
 *
 */
public class DisabledModelFactory {

	/**
	 * 
	 * @param method
	 *            can be a getterMethod of a {@link DomainObjectProperty} or a
	 *            {@link ActionMethod}
	 * @return A {@link DisabledModel} that checks if an item is disabled or not
	 *         (at runtime)
	 */
	public static DisabledModel create(AuthorizationProvider authorizationProvider, Method method) {

		Optional<DisabledMethodModel> disabledMethodModel = createDisabledMethodModel(method);
		Optional<DisabledAnnotationModel> disabledAnnotationModel = createDisabledAnnotationModel(authorizationProvider,
				method);

		boolean hasAnnotation = disabledAnnotationModel.isPresent();
		boolean hasMethod = disabledMethodModel.isPresent();
		
		if (hasAnnotation && !hasMethod) {
			return disabledAnnotationModel.get();
		} else if (!hasAnnotation && hasMethod) {
			return disabledMethodModel.get();
		} else if (hasAnnotation && hasMethod) {
			return new DisabledOrModel(disabledAnnotationModel.get(), disabledMethodModel.get());
		} else { // !hasAnnotation && !hasMethod
			return DisabledFalseModel.getInstance();
		}
	}

	private static Optional<DisabledAnnotationModel> createDisabledAnnotationModel(
			AuthorizationProvider authorizationProvider, Method method) {
		Disabled disabledAnnotation = method.getAnnotation(Disabled.class);
		if (disabledAnnotation == null) {
			return Optional.empty();
		} else {
			return Optional.of(new DisabledAnnotationModel(authorizationProvider, disabledAnnotation));
		}
	}

	private static Optional<DisabledMethodModel> createDisabledMethodModel(Method method) {
		Optional<Method> disabledMethod = BehavioralMethods.DISABLED.findFor(method);
		if (disabledMethod.isPresent()) {
			return Optional.of(new DisabledMethodModel(disabledMethod.get()));
		} else {
			return Optional.empty();
		}
	}

	public static DisabledModel create(AuthorizationProvider authorizationProvider, Method getterMethod,
			Method setterMethod) {
		if (setterMethod == null) {
			return DisabledTrueModel.getInstance();
		} else {
			return create(authorizationProvider, getterMethod);
		}
	}
}
