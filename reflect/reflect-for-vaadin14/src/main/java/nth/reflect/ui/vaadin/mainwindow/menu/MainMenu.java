package nth.reflect.ui.vaadin.mainwindow.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import nth.reflect.fw.gui.component.mainmenu.MainMenuItems;
import nth.reflect.fw.gui.item.method.MethodOwnerItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;

public class MainMenu extends Tabs implements nth.reflect.fw.gui.component.mainmenu.MainMenu {

	private static final long serialVersionUID = 8100689759722077427L;

	public MainMenu(UserInterfaceContainer userInterfaceContainer) {
		setOrientation(Orientation.VERTICAL);
		add(createItems(userInterfaceContainer));

	}

	public Tab[] createItems(UserInterfaceContainer userInterfaceContainer) {

		Collection<MethodOwnerItem> serviceObjectItems = new MainMenuItems(userInterfaceContainer);

		List<Tab> menuItems = new ArrayList<>();

		for (MethodOwnerItem serviceObjectItem : serviceObjectItems) {

			List<Tab> actionMethodButtons = new ArrayList<>();

			for (Item actionMethodItem : serviceObjectItem.getChildren()) {
				ActionMethodButton actionMethodButton = new ActionMethodButton(actionMethodItem);
				actionMethodButtons.add(actionMethodButton);
			}

			ServiceObjectButton serviceObjectButton = new ServiceObjectButton(serviceObjectItem, actionMethodButtons);
			menuItems.add(serviceObjectButton);
			menuItems.addAll(actionMethodButtons);
		}
		return menuItems.toArray(new Tab[menuItems.size()]);
	}
}
