package nth.reflect.fw.swing.tab.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.swing.util.ColorFactory;

public class MenuList extends JList<Item> {

	private static final long serialVersionUID = 6951897887401103964L;

	public MenuList(UserInterfaceContainer userInterfaceContainer) {
		super(new MenuListModel(userInterfaceContainer));
		ColorProvider colorProvider=userInterfaceContainer.get(ColorProvider.class);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.VERTICAL);
		setCellRenderer(new MenuListRenderer(colorProvider));
		setVisibleRowCount(-1);
		addMouseListener(createMouseListener());
		addKeyListener(createKeyListener());
		setBackground(ColorFactory.create(colorProvider.getContentColors().getBackground()));
	}

	private KeyListener createKeyListener() {
		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
					onActionEvent();
				}
			}
		};
		return keyAdapter;
	}

	private MouseListener createMouseListener() {
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					onActionEvent();
				}
			}
		};
		return mouseListener;
	}

	public void onActionEvent() {
		Item selectedItem = getSelectedValue();
		System.out.println("Action: " + selectedItem.getText());
		selectedItem.getAction().run();
	}

}
