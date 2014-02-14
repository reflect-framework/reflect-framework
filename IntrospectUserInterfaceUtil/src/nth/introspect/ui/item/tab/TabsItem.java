package nth.introspect.ui.item.tab;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.images.IntrospectImage;
import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.ui.item.Item;
import nth.introspect.ui.item.SeparatorItem;

public class TabsItem extends HierarchicalItem {

	public TabsItem(View view) {
		setText("Tabs");
		setDescription("Tabs menu");
		setIconURI(Introspect.getPathProvider().getImagePath(IntrospectImage.TABS));

		@SuppressWarnings("unchecked")
		ViewContainer<View> viewContainer = Introspect.getUserInterfaceProvider().getViewContainer();
		List<Item> children = new ArrayList<Item>();
		children.add(new CloseThisTabItem(view));
		children.add(new CloseOtherTabsItem(view));
		children.add(new CloseAllTabsItem());
		if (viewContainer.getViewCount() > 1) {
			children.add(new SeparatorItem());
			for (int i = 0; i < viewContainer.getViewCount(); i++) {
				view = viewContainer.getView(i);
				children.add(new SelectTabItem(view));
			}
		}

		getChildren().addAll(children);

	}
}
