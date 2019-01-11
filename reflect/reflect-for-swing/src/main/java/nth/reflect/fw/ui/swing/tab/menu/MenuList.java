package nth.reflect.fw.ui.swing.tab.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import nth.reflect.fw.gui.style.ReflectColors;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.swing.util.ColorFactory;

public class MenuList extends JList<Item> {

	private static final long serialVersionUID = 6951897887401103964L;

	public MenuList(UserInterfaceContainer userInterfaceContainer) {
		super(new MenuListModel(userInterfaceContainer));
		ReflectColors reflectColors=ReflectColors.getFrom(userInterfaceContainer);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.VERTICAL);
		setCellRenderer(new MenuListRenderer(reflectColors));
		setVisibleRowCount(-1);
		addMouseListener(createMouseListener());
		addKeyListener(createKeyListener());
		setBackground(ColorFactory.create(reflectColors.getContentColors().getBackground()));
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
