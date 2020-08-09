package nth.reflect.fw.vaadin.layer5provider.actionmethod.prehandler;

import nth.reflect.fw.vaadin.layer5provider.actionmethod.prehandler.impl.EditDomainObject;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.prehandler.impl.SelectFileToUpload;

public class ActionMethodPreHandlerClasses
		extends nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerClasses {

	private static final long serialVersionUID = -7609084438363541421L;

	@Override
	public Class<? extends EditDomainObject> getEditDomainObject() {
		return EditDomainObject.class;
	}

	@Override
	public Class<? extends SelectFileToUpload> getSelectFileToUpload() {
		return SelectFileToUpload.class;
	}

}
