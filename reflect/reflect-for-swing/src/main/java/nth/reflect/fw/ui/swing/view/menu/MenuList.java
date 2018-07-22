package nth.reflect.fw.ui.swing.view.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.swing.view.menu.item.Item;

public class MenuList extends JList<Item> {

	private static final long serialVersionUID = 6951897887401103964L;

	public MenuList(DependencyInjectionContainer userInterfaceContainer) {
		super(new MenuListModel(userInterfaceContainer));
		GraphicalUserinterfaceController<?> controller = userInterfaceContainer.get(GraphicalUserinterfaceController.class);
		MaterialStyle materialStyle=controller.getMaterialStyle();
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.VERTICAL);
		setCellRenderer(new MenuListRenderer(materialStyle));
		setVisibleRowCount(-1);
		addMouseListener(createMouseListener());
		addKeyListener(createKeyListener());
		setBackground(ColorFactory.create(materialStyle.getListSingleLineStyle().getBackgroundColor()));
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
		selectedItem.onAction();
	}

}
