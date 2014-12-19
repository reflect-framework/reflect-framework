package nth.introspect.ui.swing.view.menu;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.ui.swing.SwingUserinterfaceProvider;
import nth.introspect.ui.swing.mainwindow.MainWindow;
import nth.introspect.ui.swing.view.container.SwingViewContainer;

public class MenuTabPanel extends SwingViewContainer {

	private static final long serialVersionUID = -3050106464316936346L;
	private MenuView menuView;

	public MenuTabPanel(UserInterfaceContainer introspectouterContainer) {
		menuView = new MenuView(introspectouterContainer);
		addView( menuView);
	}

	/**
	 * This method is overridden to prevent that the menu tab is removed when closed by the user.<br>
	 * Instead we call {@link MainWindow#hideMenu()}
	 */
	@Override
	public void removeTabAt(int index) {
		SwingUserinterfaceProvider userinterfaceProvider = (SwingUserinterfaceProvider) Introspect.getUserInterfaceProvider();
		MainWindow mainWindow = userinterfaceProvider.getMainWindow();
		mainWindow.hideMenu();
	}

	public MenuView getMenuPanel() {
		return menuView;
	}
}
