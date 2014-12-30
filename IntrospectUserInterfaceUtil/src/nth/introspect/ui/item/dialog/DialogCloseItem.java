package nth.introspect.ui.item.dialog;

import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.item.Item;

//TODO move to UserinterfaceUtil project
public class DialogCloseItem extends Item {

	public DialogCloseItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText("Close");
		setDescription("Close this dialog");
		//no action. Dialog will close it self
	}
}
