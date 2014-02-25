package nth.introsepect.ui.swing.item.menu;

import javax.swing.JMenuItem;

import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.item.HierarchicalItem;

public class MenuItemFactory {
	public static JMenuItem create(Item item) {
		if (item instanceof HierarchicalItem) {
			HierarchicalItem hierarchicalItem = (HierarchicalItem) item;
			if (hierarchicalItem.getSize() == 0) {
				return new MenuItem(item);
			} else {
				return new Menu(hierarchicalItem);
			}
		}
		return new MenuItem(item);
	}
}
