package nth.introspect.ui.swing.view.menu;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.ui.swing.UserinterfaceControllerForSwing;
import nth.introspect.ui.swing.mainwindow.MainWindow;
import nth.introspect.ui.swing.view.container.SwingViewContainer;

public class MenuTabPanel extends SwingViewContainer {

	private static final long serialVersionUID = -3050106464316936346L;
	private MenuView menuView;
	private UserInterfaceContainer userInterfaceContainer;

	public MenuTabPanel(UserInterfaceContainer userInterfaceContainer, PathProvider pathProvider) {
		super(userInterfaceContainer, pathProvider);
		this.userInterfaceContainer = userInterfaceContainer;
		menuView = new MenuView(userInterfaceContainer, pathProvider );
		addView(menuView);
	}

	/**
	 * This method is overridden to prevent that the menu tab is removed when
	 * closed by the user.<br>
	 * Instead we call {@link MainWindow#hideMenu()}
	 */
	@Override
	public void removeTabAt(int index) {
		UserinterfaceControllerForSwing swingUserinterfaceController = (UserinterfaceControllerForSwing) userInterfaceContainer
				.getUserInterfaceController();
		MainWindow mainWindow = swingUserinterfaceController.getMainWindow();
		mainWindow.hideMenu();
	}

	public MenuView getMenuPanel() {
		return menuView;
	}
}
