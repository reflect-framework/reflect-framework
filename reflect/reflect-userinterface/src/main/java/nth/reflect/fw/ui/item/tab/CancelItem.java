package nth.reflect.fw.ui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.generic.translatablestring.ReflectTranslatable;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.tab.Tab;
import nth.reflect.fw.ui.tab.Tabs;


public class CancelItem<TAB extends Tab> extends Item {
	@ReflectTranslatable
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
