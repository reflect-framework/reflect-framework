package nth.introsepect.ui.swing.view.menu;

import nth.introsepect.ui.swing.SwingUserinterfaceProvider;
import nth.introsepect.ui.swing.mainwindow.MainWindow;
import nth.introsepect.ui.swing.view.container.SwingViewContainer;
import nth.introspect.Introspect;

public class MenuTabPanel extends SwingViewContainer {

	private static final long serialVersionUID = -3050106464316936346L;
	private MenuView menuView;

	public MenuTabPanel() {
		menuView = new MenuView();
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
