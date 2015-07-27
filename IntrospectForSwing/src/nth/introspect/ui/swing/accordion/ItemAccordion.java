package nth.introspect.ui.swing.accordion;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListModel;

import nth.introspect.layer5provider.userinterface.item.Item;
import nth.introspect.ui.item.method.MethodItem;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.swing.icon.IconFactory;
import nth.introspect.ui.swing.view.menu.MenuListRenderer;

@SuppressWarnings("serial")
public class ItemAccordion extends Accordion {

	private static final int ITEM_HEIGHT = 25;

	public ItemAccordion(List<MethodOwnerItem> menuItems) {
		for (MethodOwnerItem item : menuItems) {
			addBar(new ServiceObjectBar(item));
		}
	}

	public void select(Item selectedItem) {
		Collection<BarInfo> bars = getBars();

		// get service and method item to find
		MethodOwnerItem serviceItemToFind = null;
		MethodItem methodItemToFind = null;
		if (selectedItem instanceof MethodOwnerItem) {
			serviceItemToFind = (MethodOwnerItem) selectedItem;
		} else if (selectedItem instanceof MethodItem) {
			methodItemToFind = (MethodItem) selectedItem;
			// TODO serviceItemToFind = (MethodOwnerItem)
			// methodItemToFind.getParent();
		} else {
			return;
		}

		// find service object bar in accordion
		int i = 0;
		for (BarInfo bar : bars) {
			if (bar instanceof ServiceObjectBar) {
				ServiceObjectBar serviceObjectBar = (ServiceObjectBar) bar;
				if (serviceItemToFind.equals(serviceObjectBar
						.getMethodOwnerItem())) {
					// open service object bar in accordion
					setVisibleBar(i);

					// find method item in list

					JComponent component = serviceObjectBar.getComponent();
					if (component instanceof JList) {
						JList list = (JList) component;
						ListModel listModel = list.getModel();
						if (methodItemToFind != null) {
							for (int j = 0; j < listModel.getSize(); j++) {
								Object listItem = listModel.getElementAt(j);
								if (listItem instanceof MethodItem) {
									MethodItem methodItem = (MethodItem) listItem;
									if (methodItemToFind.equals(methodItem)) {
										// open method item in list
										list.setSelectedValue(methodItem, true);
										return;
									}
								}
							}
						}
						list.setSelectedIndex(-1);// select none
					}
				}
			}
			i++;
		}

	}

	private JList createMenuList(MethodOwnerItem methodOwnerItem) {
		List<Item> methodItems = methodOwnerItem.getChildren();
		Item[] array = methodItems.toArray(new Item[methodItems.size()]);
		final JList menuList = new JList(array);
		menuList.setCellRenderer(new MenuListRenderer());
		menuList.setFixedCellHeight(ITEM_HEIGHT);
		menuList.addKeyListener(createMenuListKeyListener());
		menuList.addMouseListener(createMenuListMouseListener());
		menuList.setSelectedIndex(0);
		menuList.setRequestFocusEnabled(true); //TODO Does not work
		menuList.setFocusable(true);
		
		return menuList;
	}

	private MouseListener createMenuListMouseListener() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				onMenuItemClick((JList) e.getSource());
			}
		};
	}

	private KeyListener createMenuListKeyListener() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyChar()) {
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_SPACE:
					onMenuItemClick((JList) e.getSource());
					break;
				case KeyEvent.VK_UP:
					onFocusPrevious();
					break;
				case KeyEvent.VK_DOWN:
					onFocusNext();
					break;
				default:
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
	}

	protected void onFocusNext() {
		System.out.println("Next");//TODO does not work
	}

	protected void onFocusPrevious() {
		System.out.println("Previous");//TODO does not work
	}

	protected void onMenuItemClick(JList menuList) {
		int index = menuList.getSelectedIndex();
		Item item = (Item) menuList.getModel().getElementAt(index);
		item.getAction().run();
	}

	public class ServiceObjectBar extends BarInfo {

		private final MethodOwnerItem methodOwnerItem;

		public ServiceObjectBar(MethodOwnerItem methodOwnerItem) {
			super("", createMenuList(methodOwnerItem));
			this.methodOwnerItem = methodOwnerItem;
			getButton().setText(methodOwnerItem.getText());
			getButton().setToolTipText(methodOwnerItem.getDescription());
			getButton().setIcon(
					IconFactory.create(methodOwnerItem.getIconURI()));
		}

		public MethodOwnerItem getMethodOwnerItem() {
			return methodOwnerItem;
		}

	}

}
