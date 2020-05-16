package nth.reflect.fw.swing.item.popupmenu;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.swing.item.menu.MenuItemFactory;

/**
 * This PopupMenu is an adapter for the JPopupmenu to work with {@code Item}<br>
 * 
 * @author Nils ten Hoeve
 * 
 */
public class PopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -2383997305229801062L;

	public PopupMenu(Collection<Item> items) {
		populate(items);
	}

	public void repopulate(Collection<Item> items) {
		removeAll();
		populate(items);
	}

	private void populate(Collection<Item> items) {
		for (Item item : items) {
			JMenuItem menuItem = MenuItemFactory.create(item);
			add(menuItem);
		}
	}

	@Override
	public void show(Component invoker, int x, int y) {
		super.show(invoker, x, y);
		// select first visible item (not default behavior but I prefer it anyway)  
		dispatchEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0,
				KeyEvent.VK_DOWN, '\0'));
	}

}
