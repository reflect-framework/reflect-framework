package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethod;

/**
 * <p>
 * The {@link Options} method returns a {@link java.util.List} with values that
 * can be selected for a {@link DomainObjectProperty} e.g. with a
 * <a href="https://en.wikipedia.org/wiki/Combo_box" >Combo Box</a>
 * </p>
 * <p>
 * Syntax: public {@link List}&lt;{@link DomainObjectProperty}Type &gt;
 * &lt;{@link DomainObjectProperty} name&gt;Options();
 * </p>
 * <p>
 * Return Value: A {@link List} with values of the {@link DomainObjectProperty}
 * Type. This {@link java.util.List} should not be empty because than a user can
 * not select a value. The {@link java.util.List} may contain a null value when
 * the {@link DomainObjectProperty} may be null. It is best to use an ordered
 * {@link java.util.List} (e.g. an {@link ArrayList}) so that the items stay in
 * the same sequence for the user to choose from.
 * </p>
 * 
 * 
 * @author nilsth
 *
 */
public class OptionsMethod extends BehavioralMethod {

	@Override
	public String getBehavioralName() {
		return "Options";
	}

	@Override
	public Class<?> getReturnType() {
		return List.class;
	}

}
