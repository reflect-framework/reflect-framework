package nth.reflect.fw.javafx.layer5provider.actionmethod.prehandler;

import nth.reflect.fw.javafx.layer5provider.actionmethod.prehandler.impl.EditDomainObject;
import nth.reflect.fw.javafx.layer5provider.actionmethod.prehandler.impl.SelectFileToUpload;

public class ActionMethodPreHandlerClasses
		extends nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerClasses {

	private static final long serialVersionUID = -1385199553569811879L;

	@Override
	public Class<? extends EditDomainObject> getEditDomainObject() {
		return EditDomainObject.class;
	}

	@Override
	public Class<? extends SelectFileToUpload> getSelectFileToUpload() {
		return SelectFileToUpload.class;
	}

}
