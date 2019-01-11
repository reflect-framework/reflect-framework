package nth.reflect.fw.gui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class CloseThisTabItem<TAB extends Tab> extends Item {

	private static final String CLOSE_THIS_TAB = "Close this tab";

	public CloseThisTabItem(LanguageProvider languageProvider,
			final Tabs<TAB> tabs, final TAB tabToClose) {
		super(languageProvider);
		setText(CLOSE_THIS_TAB);
		setDescription(CLOSE_THIS_TAB);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE ));
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
