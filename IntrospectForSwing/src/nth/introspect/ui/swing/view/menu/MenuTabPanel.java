package nth.introspect.ui.swing.view.menu;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.swing.UserinterfaceControllerForSwing;
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
		UserinterfaceControllerForSwing swingUserinterfaceController = userInterfaceContainer
				.get(UserinterfaceControllerForSwing.class);
		MainWindow mainWindow = swingUserinterfaceController.getMainWindow();
		mainWindow.hideMenu();
	}

	public MenuView getMenuPanel() {
		return menuView;
	}
}
