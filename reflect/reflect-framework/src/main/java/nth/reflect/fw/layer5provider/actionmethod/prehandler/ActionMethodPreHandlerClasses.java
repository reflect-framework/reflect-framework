package nth.reflect.fw.layer5provider.actionmethod.prehandler;

import java.util.ArrayList;

import nth.reflect.fw.layer5provider.actionmethod.prehandler.impl.AskConfirmation;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.impl.EditDomainObject;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.impl.ProcessDirectly;

public class ActionMethodPreHandlerClasses extends ArrayList<Class<? extends ActionMethodPreHandler>> {

	private static final long serialVersionUID = -3809070837215143050L;

	public ActionMethodPreHandlerClasses() {
		add(getEditDomainObject());
		add(getAskConfirmation());
		add(getProcessDirectly());
	}

	public Class<? extends EditDomainObject> getEditDomainObject() {
		return EditDomainObject.class;
	}

	public Class<? extends AskConfirmation> getAskConfirmation() {
		return AskConfirmation.class;
	}

	public Class<? extends ProcessDirectly> getProcessDirectly() {
		return ProcessDirectly.class;
	}

}
