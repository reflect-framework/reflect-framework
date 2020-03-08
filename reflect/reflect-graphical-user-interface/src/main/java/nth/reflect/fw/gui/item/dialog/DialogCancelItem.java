package nth.reflect.fw.gui.item.dialog;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

//TODO move to UserinterfaceUtil project
public class DialogCancelItem extends Item {

	private static final TranslatableString CANCEL_TEXT = new TranslatableString(
			DialogCancelItem.class.getCanonicalName() + ".text", "Cancel");
	private static final TranslatableString CANCEL_DESCRIPTION = new TranslatableString(
			DialogCancelItem.class.getCanonicalName() + ".description", "Cancel and close this dialog");

	public DialogCancelItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText(CANCEL_TEXT);
		setDescription(CANCEL_DESCRIPTION);
		// no action. Dialog will close it self
	}
}
