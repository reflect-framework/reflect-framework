package nth.introspect.ui.item.dialog;

import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.userinterface.item.Item;

//TODO move to UserinterfaceUtil project
public class DialogCancelItem extends Item {

	public DialogCancelItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText("Cancel");
		setDescription("Cancel and close this dialog");
		//no action. Dialog will close it self
	}
}
