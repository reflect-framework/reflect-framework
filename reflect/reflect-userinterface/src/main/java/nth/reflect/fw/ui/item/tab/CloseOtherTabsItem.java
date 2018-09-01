package nth.reflect.fw.ui.item.tab;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

public class CloseOtherTabsItem extends Item {

	private static final String CLOSE_OTHER_TABS = "Close other tabs";
	private final ViewController<View> viewController;

	public CloseOtherTabsItem(LanguageProvider languageProvider, final ViewController<View> viewContainer, final View tabNotToBeClosed ) {
		super(languageProvider);
		this.viewController=viewContainer;
		setText(CLOSE_OTHER_TABS);
		setDescription(CLOSE_OTHER_TABS);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE));
		} catch (MalformedURLException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				for (int i = viewContainer.getViewCount() - 1; i >= 0; i--) {
					View view = viewContainer.getView(i);
					if (view != tabNotToBeClosed) {
						viewContainer.removeView(view);
					}
				}
			}
		});
	}
	
	@Override
	public boolean isEnabled() {
		return viewController.getViewCount() > 1;
	}

}
