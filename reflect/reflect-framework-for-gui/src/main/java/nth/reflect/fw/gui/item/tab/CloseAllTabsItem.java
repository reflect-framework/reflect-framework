package nth.reflect.fw.gui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CloseAllTabsItem<TAB extends Tab> extends Item {

	private static final TranslatableString CLOSE_ALL_TABS_TEXT = new TranslatableString(
			CloseAllTabsItem.class.getCanonicalName() + ".text", "Close all tabs");
	private final Tabs<TAB> tabs;

	public CloseAllTabsItem(LanguageProvider languageProvider, final Tabs<TAB> tabs) {
		super(languageProvider);
		this.tabs = tabs;
		setText(CLOSE_ALL_TABS_TEXT);
		setDescription(CLOSE_ALL_TABS_TEXT);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE));
		} catch (MalformedURLException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				for (int i = tabs.size() - 1; i >= 0; i--) {
					TAB tab = tabs.get(i);
					tabs.remove(tab);
				}
			}
		});

	}

	@Override
	public boolean isEnabled() {
		return tabs.size() > 1;
	}

}
