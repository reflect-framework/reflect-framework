 package nth.reflect.fw.ui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

public class CloseAllTabsItem extends Item {

	private static final String CLOSE_ALL_TABS = "Close all tabs";
	private final ViewContainer<View> viewContainer;

	public CloseAllTabsItem(LanguageProvider languageProvider,
			final ViewContainer<View> viewContainer) {
		super(languageProvider);
		this.viewContainer = viewContainer;
		setText(CLOSE_ALL_TABS);
		setDescription(CLOSE_ALL_TABS);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE));
		} catch (MalformedURLException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				for (int i = viewContainer.getViewCount() - 1; i >= 0; i--) {
					View view = viewContainer.getView(i);
					viewContainer.removeView(view);
				}
			}
		});

	}

	@Override
	public boolean isEnabled() {
		return viewContainer.getViewCount() > 1;
	}

}
