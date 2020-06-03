package nth.reflect.fw.gui.item.dialog;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class DialogCloseItem extends Item {

	private static final TranslatableString CLOSE_TEXT = new TranslatableString(
			DialogCloseItem.class.getCanonicalName() + ".text", "Close");
	private static final TranslatableString CLOSE_DESCRIPTION = new TranslatableString(
			DialogCloseItem.class.getCanonicalName() + ".description", "Close this dialog");

	public DialogCloseItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText(CLOSE_TEXT);
		setDescription(CLOSE_DESCRIPTION);
		setAction(createAction());
	}

	private Action createAction() {
		return new Action() {

			@Override
			public void run() {
				// no action. Dialog will close it self
			}
		};
	}

}
