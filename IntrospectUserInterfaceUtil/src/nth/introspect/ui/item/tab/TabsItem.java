package nth.introspect.ui.item.tab;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.images.IntrospectImage;
import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.ui.item.SeparatorItem;

public class TabsItem extends HierarchicalItem {

	public TabsItem(LanguageProvider languageProvider, ViewContainer<View> viewContainer, View view) {
		super(languageProvider);
		setText("Tabs");
		setDescription("Tabs menu");
		setIconURI(Introspect.getPathProvider().getImagePath(IntrospectImage.TABS));

		@SuppressWarnings("unchecked")
		List<Item> children = new ArrayList<Item>();
		children.add(new CloseThisTabItem(languageProvider, viewContainer, view));
		children.add(new CloseOtherTabsItem(languageProvider,viewContainer,view));
		children.add(new CloseAllTabsItem(languageProvider,viewContainer));
		if (viewContainer.getViewCount() > 1) {
			children.add(new SeparatorItem());
			for (int i = 0; i < viewContainer.getViewCount(); i++) {
				view = viewContainer.getView(i);
				children.add(new SelectTabItem(languageProvider,viewContainer,view));
			}
		}

		getChildren().addAll(children);

	}
}
