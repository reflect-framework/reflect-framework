package nth.reflect.fw.gui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;


public class CancelItem<TAB extends Tab> extends Item {
	@Translatable
	private static final String CANCEL = "Cancel";

	public CancelItem(LanguageProvider languageProvider, final Tabs<TAB> tabs,  final TAB tabToClose ) {
		super(languageProvider);
		setText(CANCEL);
		setDescription(CANCEL);
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
