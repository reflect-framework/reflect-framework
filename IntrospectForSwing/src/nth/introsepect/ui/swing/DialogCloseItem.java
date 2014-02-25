package nth.introsepect.ui.swing;

import nth.introspect.provider.userinterface.item.Item;

//TODO move to UserinterfaceUtil project
public class DialogCloseItem extends Item {

	public DialogCloseItem() {
		setText("Close");
		setDescription("Close this dialog");
		//no action. Dialog will close it self
	}
}
