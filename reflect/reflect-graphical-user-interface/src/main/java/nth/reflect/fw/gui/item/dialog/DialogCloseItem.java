package nth.reflect.fw.gui.item.dialog;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class DialogCloseItem extends Item {

	public DialogCloseItem(LanguageProvider languageProvider) {
		super(languageProvider);
		setText("Close");
		setDescription("Close this dialog");
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
