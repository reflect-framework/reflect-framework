package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * When adding the {@link ParameterFactory} method (normally located after an
 * {@link ActionMethod}), the {@link UserInterfaceController} will first get a
 * new {@link DomainObject} from the {@link ParameterFactoryMethod}. This
 * object can then be edited by the user (depending how the {@link ActionMethod}
 * is annotated, see {@link ExecutionMode}) after which it is passed as method
 * parameter when the {@link ActionMethod} is invoked.
 * </p>
 * <p>
 * Syntax: public&lt;domainObject type&gt;
 * prameterFactory&lt;actionMethodName&gt;()
 * </p>
 * <p>
 * TODO EXAMPLE OF ordersWithinPeriod METHOD
 * 
 * @author nilsth
 *
 */
public class ParameterFactoryMethod extends BehavioralMethod{

	@Override
	public String getBehavioralName() {
		return "ParameterFactory";
	}

	@Override
	public Class<?> getReturnType() {
		return Object.class;
	}

}
