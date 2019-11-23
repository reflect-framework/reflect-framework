package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.lang.reflect.Method;
import java.util.Optional;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;

/**
 * <p>
 * A {@link DomainObjectProperty} can sometimes only have a limited amount of
 * valid values. Such an {@link DomainObjectProperty} can be displayed as a
 * <a href="https://en.wikipedia.org/wiki/Combo_box" >Combo Box</a> to select
 * one of these values when its been edited.
 * </p>
 * <h3>Option Method</h3>
 * <p>
 * {@insert OptionsMethod}
 * </p>
 * 
 * 
 * @author nilsth
 *
 */

public class OptionsModelFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static OptionsModel create(Method getMethod) {
		Optional<Method> foundOptionsMethod = BehavioralMethods.OPTIONS.findFor(getMethod);
		if (foundOptionsMethod.isPresent()) {
			return new OptionsMethodModel(foundOptionsMethod.get());
		} else if (getMethod.getReturnType().isEnum()) {
			return new OptionsEnumModel((Class<? extends Enum>) getMethod.getReturnType());
		} else {
			return new OptionsNotExistingModel();
		}
	}

}
