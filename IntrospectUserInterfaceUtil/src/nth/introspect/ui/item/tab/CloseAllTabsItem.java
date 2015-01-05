package nth.introspect.ui.item.tab;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.images.IntrospectImage;

public class CloseAllTabsItem extends Item {

	private static final String CLOSE_ALL_TABS = "Close all tabs";
	private final ViewContainer<View> viewContainer;

	@SuppressWarnings("unchecked")
	public CloseAllTabsItem(PathProvider pathProvider, LanguageProvider languageProvider, final ViewContainer<View> viewContainer) {
		super(languageProvider);
		this.viewContainer=viewContainer;
		setText(CLOSE_ALL_TABS);
		setDescription(CLOSE_ALL_TABS);
		setIconURI(pathProvider.getImagePath(IntrospectImage.TABS_CLOSE_ALL));
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
