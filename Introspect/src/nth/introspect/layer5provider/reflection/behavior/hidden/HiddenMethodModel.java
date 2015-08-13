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
 * <p>
 * You can hide a {@link DomainObjectProperty} or
 * {@link DomainObjectActionMethod} depending on the {@link DomainObject} state
 * (the value of its properties), with an  ...Hidden method. If you have a {@link Hidden} annotation and a
 * ...Hidden method, both need to be true in order to hide the
 * {@link DomainObjectProperty} or {@link ActionMethod}. 
 * </p>
 * <p>
 * Note that you can not change the visibility with a ...Hidden method for {@link DomainObjectProperty} in a {@link TableView}!
 * </p>
 * <p>
 * Syntax: public boolean &lt;property name or actionMethodName&gtHidden;()
 * </p>
 * <p>
 * ReturnValue: a boolean that is true if the {@link DomainObjectProperty} or
 * {@link ActionMethod} needs to be hidden
 * </p>
 * 
 * @author nilsth
 *
 */
public class HiddenMethodModel extends BehavioralMethod implements HiddenModel {

	private Method hiddenMethod;

	public HiddenMethodModel(Method hiddenMethod) {
		this.hiddenMethod = hiddenMethod;
	}

	@Override
	public String getBehavioralName() {
		return "Hidden";
	}

	@Override
	public Class<?> getReturnType() {
		return boolean.class;
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
