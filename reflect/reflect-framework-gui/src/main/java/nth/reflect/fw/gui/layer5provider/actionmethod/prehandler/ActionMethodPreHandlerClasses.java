package nth.reflect.fw.gui.layer5provider.actionmethod.prehandler;

import java.util.ArrayList;

import nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.AskConfirmation;
import nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.EditDomainObject;
import nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.SelectFileToUpload;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.impl.ProcessDirectly;

public abstract class ActionMethodPreHandlerClasses extends ArrayList<Class<? extends ActionMethodPreHandler>> {

	private static final long serialVersionUID = -3809070837215143050L;

	public ActionMethodPreHandlerClasses() {
		add(getAskConfirmation());
		add(getSelectFileToUpload());
		add(getEditDomainObject());
		add(getProcessDirectly());
	}

	public Class<? extends AskConfirmation> getAskConfirmation() {
		return AskConfirmation.class;
	}

	public abstract Class<? extends SelectFileToUpload> getSelectFileToUpload();

	public abstract Class<? extends EditDomainObject> getEditDomainObject();

	public Class<? extends ProcessDirectly> getProcessDirectly() {
		return ProcessDirectly.class;
	}

}
