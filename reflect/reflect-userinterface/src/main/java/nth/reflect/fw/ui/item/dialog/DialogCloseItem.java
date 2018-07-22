package nth.reflect.fw.ui.item.dialog;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

//TODO move to UserinterfaceUtil project
public class DialogCloseItem extends Item {

	public DialogCloseItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText("Close");
		setDescription("Close this dialog");
		//no action. Dialog will close it self
	}
}
