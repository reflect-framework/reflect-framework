package nth.reflect.fw.layer5provider.actionmethodexecution.result;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * Shows the return value from an {@link ActionMethod}, if the return value type
 * is a {@link DomainObject}
 * 
 * @author nilsth
 *
 */
public abstract class DomainObjectResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo actionMethodInfo) {
		boolean returntTypeIsDomainClass = actionMethodInfo.getReturnTypeInfo().isDomainClass();
		return returntTypeIsDomainClass;
	}

}
