package nth.reflect.fw.ui.item.method.menu;

import java.util.Collection;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.component.mainmenu.MainMenu;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;

/**
 * Menu items for in a {@link MainMenu}
 * @author nilsth
 *
 */
public class MainMenuItems extends UnmodifiableCollection<MethodOwnerItem> {

	private static final long serialVersionUID = -7940254102480175383L;

	public MainMenuItems(UserInterfaceContainer userInterfaceContainer) {
		super(createMainMenuItems(userInterfaceContainer));
	}

	private static Collection<? extends MethodOwnerItem> createMainMenuItems(
			UserInterfaceContainer userInterfaceContainer) {
		return new ServiceObjectItems(userInterfaceContainer);
	}

}
