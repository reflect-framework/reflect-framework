package nth.reflect.fw.ui.swing.item.menubar;

import java.awt.Insets;
import java.util.Collection;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.swing.item.menu.MenuItemFactory;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -2631861999697579802L;

	public MenuBar(Collection<Item> items) {
		populate(items);

	}

	public void repopulate(Collection<Item> items) {
		removeAll();
		populate(items);
	}

	private void populate(Collection<Item> items) {
		for (Item item : items) {
			JMenuItem menuItem = MenuItemFactory.create(item);
			menuItem.setMargin(new Insets(0, 10, 0, 10));
			add(menuItem);
		}
	}
}
