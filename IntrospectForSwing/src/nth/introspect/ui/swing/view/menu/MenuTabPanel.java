package nth.introspect.ui.swing.view.menu;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.ui.swing.SwingUserinterfaceProvider;
import nth.introspect.ui.swing.mainwindow.MainWindow;
import nth.introspect.ui.swing.view.container.SwingViewContainer;

public class MenuTabPanel extends SwingViewContainer {

	private static final long serialVersionUID = -3050106464316936346L;
	private MenuView menuView;
	private UserInterfaceContainer userInterfaceContainer;

	public MenuTabPanel(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		this.userInterfaceContainer = userInterfaceContainer;
		menuView = new MenuView(userInterfaceContainer);
		addView(menuView);
	}

	/**
	 * This method is overridden to prevent that the menu tab is removed when
	 * closed by the user.<br>
	 * Instead we call {@link MainWindow#hideMenu()}
	 */
	@Override
	public void removeTabAt(int index) {
		SwingUserinterfaceProvider swingUserinterfaceProvider = (SwingUserinterfaceProvider) userInterfaceContainer
				.getUserInterfaceProvider();
		MainWindow mainWindow = swingUserinterfaceProvider.getMainWindow();
		mainWindow.hideMenu();
	}

	public MenuView getMenuPanel() {
		return menuView;
	}
}
