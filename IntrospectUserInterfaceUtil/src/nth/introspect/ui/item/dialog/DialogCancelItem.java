package nth.introspect.ui.item.dialog;

import nth.introspect.provider.userinterface.item.Item;

//TODO move to UserinterfaceUtil project
public class DialogCancelItem extends Item {

	public DialogCancelItem() {
		setText("Cancel");
		setDescription("Cancel and close this dialog");
		//no action. Dialog will close it self
	}
}
