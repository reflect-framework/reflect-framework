package nth.reflect.fw.gui.item.dialog;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

//TODO move to UserinterfaceUtil project
public class DialogCancelItem extends Item {

	public DialogCancelItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText("Cancel");
		setDescription("Cancel and close this dialog");
		//no action. Dialog will close it self
	}
}
