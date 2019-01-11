package nth.reflect.fw.ui.swing.tab.menu;

import java.util.Collection;

import javax.swing.DefaultListModel;

import nth.reflect.fw.gui.item.method.MethodOwnerItem;
import nth.reflect.fw.gui.item.method.menu.MainMenuItems;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public class MenuListModel extends DefaultListModel<nth.reflect.fw.layer1userinterface.item.Item> {

	private static final long serialVersionUID = 1676652394002556458L;

	public MenuListModel(UserInterfaceContainer userInterfaceContainer) {
		populateListModel(userInterfaceContainer);
	}

	private void populateListModel(UserInterfaceContainer userInterfaceContainer) {
		Collection<MethodOwnerItem> serviceObjectItems = new MainMenuItems(userInterfaceContainer);

		for (MethodOwnerItem serviceObjectItem : serviceObjectItems) {
			addElement(serviceObjectItem);

			for (nth.reflect.fw.layer1userinterface.item.Item actionMethoditem : serviceObjectItem.getChildren()) {
				addElement(actionMethoditem);
			}

		}
	}

}
