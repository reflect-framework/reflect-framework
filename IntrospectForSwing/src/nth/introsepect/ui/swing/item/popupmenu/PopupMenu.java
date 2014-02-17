package nth.introsepect.ui.swing.item.popupmenu;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import nth.introsepect.ui.swing.item.menu.MenuItemFactory;
import nth.introspect.ui.item.Item;

/**
 * This PopupMenu is an adapter for the JPopupmenu to work with {@code Item}<br>
 * 
 * @author Nils ten Hoeve
 * 
 */
public class PopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -2383997305229801062L;

	public PopupMenu(List<Item> items) {
		populate(items);
	}

	public void repopulate(List<Item> items) {
		removeAll();
		populate(items);
	}

	private void populate(List<Item> items) {
		for (Item item : items) {
			JMenuItem menuItem = MenuItemFactory.create(item);
			add(menuItem);
		}
	}

	@Override
	public void show(Component invoker, int x, int y) {
		// TODO Auto-generated method stub
		super.show(invoker, x, y);
		// select first visible item (not default behavior but I prefer it anyway)  
		dispatchEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0,
				KeyEvent.VK_DOWN, '\0'));
	}

}
