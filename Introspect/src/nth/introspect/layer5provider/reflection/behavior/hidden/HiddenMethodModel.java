package nth.introspect.layer5provider.reflection.behavior.hidden;

import java.lang.reflect.Method;

import javax.swing.text.TableView;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectActionMethod;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * Model that returns the value of the {@link HiddenMethod}
 * 
 * @author nilsth
 *
 */
public class HiddenMethodModel implements HiddenModel {

	private Method hiddenMethod;

	public HiddenMethodModel(Method hiddenMethod) {
		this.hiddenMethod = hiddenMethod;
	}
	
	@Override
	public boolean isPropertyHiddenInForm(Object obj) {
		return getHiddenValueFromMethod(obj);
	}

	/**
	 * {@link #isPropertyHiddenInTable()} can not be set dynamically with a {@link BehavioralMethod} because:
	 * <ul>
	 * <li>We need the information how to build a table before we have the {@link DomainObject} collection</li>
	 * <li>It would be to time consuming if we need to check each {@link DomainObject} in a collection which properties (columns) to show</li>
	 * </ul> 
	 */
	@Override
	public boolean isPropertyHiddenInTable() {
		return false; 
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		return getHiddenValueFromMethod(obj);
	}

	private boolean getHiddenValueFromMethod(Object obj) {
		Object[] arguments = new Object[0];
		try {
			return (boolean) hiddenMethod.invoke(obj, arguments);
		} catch (Exception e) {
			throw new BehaviorMethodInvokeException(hiddenMethod);
		}
	}

}
