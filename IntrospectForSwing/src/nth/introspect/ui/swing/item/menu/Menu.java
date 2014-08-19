package nth.introspect.ui.swing.item.menu;

import java.awt.event.ActionEvent;
import java.net.URI;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenu;

import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.ui.swing.icon.IconFactory;

public class Menu extends JMenu {

	private static final long serialVersionUID = -8312443239565563470L;
	private final HierarchicalItem item;

	public Menu(HierarchicalItem item) {
		super();
		this.item = item;
		for (Item child : item.getChildren()) {
			if (child.isSeparator()) {
				addSeparator();
			} else {
				add(MenuItemFactory.create(child));
			}
		}

		revalidate();
		repaint();
	}

	@Override
	public boolean isVisible() {
		return item.isVisible();
	}

	@Override
	public boolean isEnabled() {
		if (item == null) {
			return super.isEnabled();
		} else {
			return item.isEnabled();
		}
	}

	@Override
	public String getText() {
		if (item == null) {
			return super.getText();
		} else {
			String text = item.getText();
			setMnemonic(getKeyEvent(text));
			return text;
		}
	}

	private char getKeyEvent(String text) {
		if (text != null && text.trim().length() > 0) {
			char ch = Character.toUpperCase(text.charAt(0));
			if (Character.isLetter(ch)) {
				return ch;
			}
		}
		return '\0';
	}

	@Override
	public String getToolTipText() {
		return item.getDescription();
	}

	@Override
	public Icon getIcon() {
		URI iconUri = item.getIconURI();
		return IconFactory.create(iconUri);
	}

	// @Override
	// public int getMenuComponentCount() {
	// return item.getSize();
	// }
	//
	// @Override
	// public Component[] getMenuComponents() {
	// List<MenuItem> menuItems = new ArrayList<MenuItem>();
	// for (HierarchicalItem hierarchicalItem : item.getChildren()) {
	// MenuItem menuItem = new MenuItem(hierarchicalItem);
	// menuItems.add(menuItem);
	// }
	// return menuItems.toArray(new MenuItem[0]);
	// }
	//
	// @Override
	// public Component getMenuComponent(int n) {
	// HierarchicalItem hierarchicalItem = item.getChildren().get(n);
	// return new MenuItem(hierarchicalItem);
	// }

	@SuppressWarnings("serial")
	@Override
	public Action getAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nth.introspect.provider.userinterface.item.Item.Action action = item.getAction();
				action.run();
			}
		};
	}

}
