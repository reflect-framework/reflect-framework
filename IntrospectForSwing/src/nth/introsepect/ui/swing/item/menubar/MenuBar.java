package nth.introsepect.ui.swing.item.menubar;

import java.awt.Insets;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nth.introsepect.ui.swing.item.menu.MenuItemFactory;
import nth.introspect.provider.userinterface.item.Item;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -2631861999697579802L;

	public MenuBar(List<Item> items) {
		populate(items);

	}

	public void repopulate(List<Item> items) {
		removeAll();
		populate(items);
	}

	private void populate(List<Item> items) {
		for (Item item : items) {
			JMenuItem menuItem = MenuItemFactory.create(item);
			menuItem.setMargin(new Insets(0, 10, 0, 10));
			add(menuItem);
		}
	}
}
