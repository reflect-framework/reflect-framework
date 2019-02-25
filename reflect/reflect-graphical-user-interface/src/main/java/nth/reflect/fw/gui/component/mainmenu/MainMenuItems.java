package nth.reflect.fw.gui.component.mainmenu;

import java.util.Collection;

import nth.reflect.fw.gui.item.method.MethodOwnerItem;
import nth.reflect.fw.gui.item.method.ServiceObjectItems;
import nth.reflect.fw.gui.util.collection.UnmodifiableCollection;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

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
