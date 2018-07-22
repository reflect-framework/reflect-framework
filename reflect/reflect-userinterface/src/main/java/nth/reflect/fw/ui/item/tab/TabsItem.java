package nth.reflect.fw.ui.item.tab;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.item.HierarchicalItem;
/**
 * TODO remove this class???
 * @author nilsth
 *
 */
public class TabsItem extends HierarchicalItem {

	public TabsItem(LanguageProvider languageProvider, ViewContainer<View> viewContainer, View view) {
		super(languageProvider);
		setText("Tabs");
		setDescription("Tabs menu");

		@SuppressWarnings("unchecked")
		List<Item> children = new ArrayList<Item>();
		children.add(new CloseThisTabItem(languageProvider, viewContainer, view));
		children.add(new CloseOtherTabsItem(languageProvider,viewContainer,view));
		children.add(new CloseAllTabsItem(languageProvider,viewContainer));
		if (viewContainer.getViewCount() > 1) {
			for (int i = 0; i < viewContainer.getViewCount(); i++) {
				view = viewContainer.getView(i);
				children.add(new SelectTabItem(languageProvider,viewContainer,view));
			}
		}

		getChildren().addAll(children);

	}
}
