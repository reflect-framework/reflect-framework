package nth.reflect.fw.gui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CancelItem<TAB extends Tab> extends Item {
	private static final TranslatableString CANCEL_TEXT = new TranslatableString(
			CancelItem.class.getCanonicalName() + ".text", "Cancel");
	private static final TranslatableString CANCEL_DESCRIPTION = new TranslatableString(
			CancelItem.class.getCanonicalName() + ".description", "Cancel changes and close this tab");

	public CancelItem(LanguageProvider languageProvider, final Tabs<TAB> tabs, final TAB tabToClose) {
		super(languageProvider);
		setText(CANCEL_TEXT);
		setDescription(CANCEL_DESCRIPTION);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE));
		} catch (MalformedURLException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				tabs.remove(tabToClose);
			}
		});
	}
}
