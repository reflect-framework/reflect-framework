package nth.introspect.ui.item.tab;

import nth.introspect.Introspect;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.ui.images.IntrospectImage;

public class CloseOtherTabsItem extends Item {

	private static final String CLOSE_OTHER_TABS = "Close other tabs";
	private final ViewContainer<View> viewContainer;

	@SuppressWarnings("unchecked")
	public CloseOtherTabsItem(PathProvider pathProvider, LanguageProvider languageProvider, final ViewContainer<View> viewContainer, final View tabNotToBeClosed ) {
		super(languageProvider);
		this.viewContainer=viewContainer;
		setText(CLOSE_OTHER_TABS);
		setDescription(CLOSE_OTHER_TABS);
		setIconURI(pathProvider.getImagePath(IntrospectImage.TABS_CLOSE_OTHERS));
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
		return viewContainer.getViewCount() > 1;
	}

}
