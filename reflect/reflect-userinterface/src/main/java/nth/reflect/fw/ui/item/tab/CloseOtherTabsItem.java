package nth.reflect.fw.ui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.tab.Tab;
import nth.reflect.fw.ui.tab.Tabs;

public class CloseOtherTabsItem<TAB extends Tab> extends Item {

	private static final String CLOSE_OTHER_TABS = "Close other tabs";
	private final Tabs<TAB> tabs;

	public CloseOtherTabsItem(LanguageProvider languageProvider, final Tabs<TAB> tabs, final TAB tabNotToBeClosed ) {
		super(languageProvider);
		this.tabs=tabs;
		setText(CLOSE_OTHER_TABS);
		setDescription(CLOSE_OTHER_TABS);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE));
		} catch (MalformedURLException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				for (int i = tabs.size() - 1; i >= 0; i--) {
					TAB tab = tabs.get(i);
					if (tab != tabNotToBeClosed) {
						tabs.remove(tab);
					}
				}
			}
		});
	}
	
	@Override
	public boolean isEnabled() {
		return tabs.size() > 1;
	}

}
