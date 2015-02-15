package nth.introspect.ui.swing.mainwindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URI;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.ui.images.IntrospectImage;
import nth.introspect.ui.item.about.AboutItem;
import nth.introspect.ui.swing.icon.IconFactory;
import nth.introspect.ui.swing.item.button.ItemIconButton;
import nth.introspect.ui.swing.style.SwingStyleConstant;
import nth.introspect.ui.swing.view.container.SwingViewContainer;
import nth.introspect.ui.swing.view.container.TabHeader;
import nth.introspect.ui.swing.view.menu.MenuTabPanel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 7688708437355470674L;
	private JSplitPane splitPanel;
	private MenuTabPanel menuTabPanel;
	private SwingViewContainer contentTabPanel;
	private JButton menuButton;
	private final UserInterfaceContainer userInterfaceContainer;
	private final LanguageProvider languageProvider;
	private final PathProvider pathProvider;
	private final UserInterfaceController<?> userInterfaceController;
	private final DomainInfoProvider domainInfoProvider;
	private final AboutProvider aboutProvider;

	public MainWindow(IntrospectApplication application,
			UserInterfaceContainer userInterfaceContainer,
			UserInterfaceController<?> userInterfaceController,
			DomainInfoProvider domainInfoProvider, PathProvider pathProvider,
			AboutProvider aboutProvider) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.userInterfaceController = userInterfaceController;
		this.domainInfoProvider = domainInfoProvider;
		this.pathProvider = pathProvider;
		this.aboutProvider = aboutProvider;
		this.languageProvider = userInterfaceContainer.getLanguageProvider();
		// Set style
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}
		setDefaultLookAndFeelDecorated(true);

		// Set window parameters
		ClassInfo applicationInfo = domainInfoProvider.getClassInfo(application
				.getClass());

		setTitle(application, applicationInfo);
		setIcon(application, applicationInfo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create window contents
		menuTabPanel = createMenuTabPanel();
		contentTabPanel = createContentTabPanel();
		splitPanel = createSplitPanel(menuTabPanel, contentTabPanel);
		getContentPane().add(splitPanel, BorderLayout.CENTER);

		menuButton = createMenuButton();
		JButton aboutButton = createAboutButton();
		JButton findButton = createFindButton(menuTabPanel);
		JButton tabButton = createTabButton(menuTabPanel);
		JToolBar toolbar = createToolBar(aboutButton, menuButton, findButton,
				tabButton);
		getContentPane().add(toolbar, BorderLayout.NORTH);

		// Display the window.
		showMenu();
		pack();
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void setIcon(IntrospectApplication application,
			ClassInfo applicationInfo) {
		try {
			URI iconUri = applicationInfo.getIconURI(application);
			Image image = Toolkit.getDefaultToolkit().getImage(iconUri.toURL());
			setIconImage(image);
		} catch (Exception exception) {
		}
	}

	private void setTitle(IntrospectApplication application,
			ClassInfo applicationInfo) {
		String title = applicationInfo.getText();
		setTitle(title);
	}

	private JToolBar createToolBar(JButton aboutButton, JButton menuButton,
			JButton findButton, JButton tabButton) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(Box.createHorizontalGlue());// align right
		toolBar.add(aboutButton);
		toolBar.add(menuButton);
		toolBar.add(findButton);
		toolBar.add(tabButton);
		return toolBar;
	}

	private JButton createAboutButton() {
		AboutItem aboutItem = new AboutItem(userInterfaceController,
				domainInfoProvider, languageProvider, aboutProvider,
				pathProvider);
		ItemIconButton aboutButton = new ItemIconButton(aboutItem);
		aboutButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		return aboutButton;
	}

	/**
	 * Creates a button to show or hide the menu.<br>
	 * The icon and tool tip will be set by the {@link #showMenu()} and
	 * {@link MainWindow#hideMenu()} methods
	 * 
	 * @return a button to show or hide the menu
	 */
	private JButton createMenuButton() {
		JButton button = new JButton();
		@SuppressWarnings("serial")
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isMenuVisible()) {
					hideMenu();
				} else {
					showMenu();
				}
			}
		};
		button.registerKeyboardAction(action,
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button.setAction(action);
		return button;
	}

	private JButton createFindButton(final MenuTabPanel menuTabPanel) {
		JButton button = new JButton();
		@SuppressWarnings("serial")
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuTabPanel.getMenuPanel().getSearchField().requestFocus();
			}
		};
		button.setAction(action);
		button.registerKeyboardAction(action,
				KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button.setToolTipText(languageProvider.getText("Find Menu Item (F3)"));
		button.setIcon(IconFactory.create(pathProvider,
				IntrospectImage.EDIT_FIND, SwingStyleConstant.ICON_SIZE));
		return button;
	}

	private JButton createTabButton(final MenuTabPanel menuTabPanel) {
		JButton button = new JButton();
		@SuppressWarnings("serial")
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedTabIndex = getViewContainer().getSelectedIndex();
				if (selectedTabIndex >= 0) {
					Component selectedTabHeader = getViewContainer()
							.getTabComponentAt(selectedTabIndex);
					if (selectedTabHeader instanceof TabHeader) {
						TabHeader tabHeader = (TabHeader) selectedTabHeader;
						tabHeader.showPopupMenu(10, 10);
					}
				}
			}
		};
		button.setAction(action);
		button.registerKeyboardAction(action,
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button.setToolTipText(languageProvider.getText("Show Tabs Menu (F4)"));
		button.setIcon(IconFactory.create(pathProvider, IntrospectImage.TABS,
				SwingStyleConstant.ICON_SIZE));
		return button;
	}

	private JSplitPane createSplitPanel(Component menuTabPanel,
			Component contentTabPanel) {
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				menuTabPanel, contentTabPanel);
		splitPanel.setResizeWeight(0.25);
		return splitPanel;
	}

	private SwingViewContainer createContentTabPanel() {
		SwingViewContainer swingViewContainer = new SwingViewContainer(
				userInterfaceContainer, pathProvider);
		return swingViewContainer;
	}

	private MenuTabPanel createMenuTabPanel() {
		return new MenuTabPanel(userInterfaceContainer, pathProvider);
	}

	public void hideMenu() {
		// hide menu tab panel
		menuTabPanel.setVisible(false);
		// hide divider
		((BasicSplitPaneUI) splitPanel.getUI()).getDivider().setVisible(false);
		// set menu button
		menuButton.setToolTipText(languageProvider.getText("Show Menu (F2)"));
		menuButton.setIcon(IconFactory.create(pathProvider,
				IntrospectImage.MENU_SHOW, SwingStyleConstant.ICON_SIZE));
	}

	public void showMenu() {
		// un-hide menu tab panel
		menuTabPanel.setVisible(true);
		// hide divider
		((BasicSplitPaneUI) splitPanel.getUI()).getDivider().setVisible(true);
		// hide menu button
		menuButton.setToolTipText(languageProvider.getText("Hide Menu (F2)"));
		menuButton.setIcon(IconFactory.create(pathProvider,
				IntrospectImage.MENU_HIDE, SwingStyleConstant.ICON_SIZE));
	}

	public boolean isMenuVisible() {
		return menuTabPanel.isVisible();
	}

	public SwingViewContainer getViewContainer() {
		return contentTabPanel;
	}
}
