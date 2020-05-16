package nth.reflect.fw.swing.mainwindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.fw.swing.UserinterfaceControllerForSwing;
import nth.reflect.fw.swing.component.tabpanel.TabPane;
import nth.reflect.fw.swing.component.toolbar.MaterialAppBar;
import nth.reflect.fw.swing.icon.IconFactory;
import nth.reflect.fw.swing.image.ReflectImage;
import nth.reflect.fw.swing.style.SwingStyleConstant;
import nth.reflect.fw.swing.tab.Tab;
import nth.reflect.fw.swing.tab.menu.MenuList;

public class MainWindow extends JFrame {

	private static final TranslatableString SHOW_MENU = new TranslatableString(
			MainWindow.class.getCanonicalName() + ".show.menu", "Show Menu (F2)");
	private static final TranslatableString HIDE_MENU = new TranslatableString(
			MainWindow.class.getCanonicalName() + ".hide.menu", "Hide Menu (F2)");

	private static final long serialVersionUID = 7688708437355470674L;
	private final JSplitPane splitPanel;
	private final JScrollPane menuPanel;
	private final TabPane tabPane;
	private final JButton menuButton;
	private final UserInterfaceContainer userInterfaceContainer;
	private final LanguageProvider languageProvider;
	private final ReflectionProvider reflectionProvider;

	public MainWindow(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		this.userInterfaceContainer = userInterfaceContainer;
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		// Set style
		try {

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}
		setDefaultLookAndFeelDecorated(true);

		// Set window parameters
		ApplicationClassInfo applicationInfo = reflectionProvider.getApplicationClassInfo();

		setTitle(applicationInfo);
		setIcon(applicationInfo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UserinterfaceControllerForSwing userInterfaceController = userInterfaceContainer
				.get(UserinterfaceControllerForSwing.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();

		// Create window contents
		menuPanel = createMenuTabPanel();
		// tabPane = createContentTabPanel();
		tabPane = new TabPane(userInterfaceContainer, tabs);
		splitPanel = createSplitPanel(menuPanel, tabPane);
		getContentPane().add(splitPanel, BorderLayout.CENTER);

		menuButton = createMenuButton();
		// JButton aboutButton = createAboutButton();
		// JButton findButton = createFindButton(menuPanel);
		// JButton tabButton = createTabButton(menuPanel);
		// JToolBar toolbar = createToolBar(aboutButton, menuButton,
		// findButton,tabButton);
		JToolBar toolbar = new MaterialAppBar(userInterfaceContainer);
		getContentPane().add(toolbar, BorderLayout.NORTH);

		// add generic keyboard listener
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new KeyboardDispatcher(tabs));

		// Display the window.
		showMenu();
		pack();
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void setIcon(ApplicationClassInfo applicationInfo) {
		try {
			URL iconUrl = applicationInfo.getIcon();
			Image image = Toolkit.getDefaultToolkit().getImage(iconUrl);
			setIconImage(image);
		} catch (Exception exception) {
		}
	}

	private void setTitle(ApplicationClassInfo applicationInfo) {
		String title = applicationInfo.getDisplayName().getTranslation();
		setTitle(title);
	}

	/**
	 * Creates a button to show or hide the menu.<br>
	 * The MaterialAppBarIcon and tool tip will be set by the {@link #showMenu()}
	 * and {@link MainWindow#hideMenu()} methods
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
		button.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button.setAction(action);
		return button;
	}

	private JSplitPane createSplitPanel(Component menuTabPanel, Component contentTabPanel) {
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuTabPanel, contentTabPanel);
		splitPanel.setResizeWeight(0.25);
		return splitPanel;
	}

	private JScrollPane createMenuTabPanel() {
		MenuList menuList = new MenuList(userInterfaceContainer);
		JScrollPane menuPanel = new JScrollPane(menuList);
		return menuPanel;
		// return new MenuTabPanel(userInterfaceContainer);
	}

	public void hideMenu() {
		// hide menu tab panel
		menuPanel.setVisible(false);
		// hide divider
		((BasicSplitPaneUI) splitPanel.getUI()).getDivider().setVisible(false);
		// set menu button
		menuButton.setToolTipText(languageProvider.getText(SHOW_MENU));
		menuButton.setIcon(IconFactory.create(ReflectImage.MENU_OPENED, SwingStyleConstant.ICON_SIZE));
	}

	public void showMenu() {
		// un-hide menu tab panel
		menuPanel.setVisible(true);
		// hide divider
		((BasicSplitPaneUI) splitPanel.getUI()).getDivider().setVisible(true);
		// hide menu button
		menuButton.setToolTipText(languageProvider.getText(HIDE_MENU));
		menuButton.setIcon(IconFactory.create(ReflectImage.MENU_CLOSED, SwingStyleConstant.ICON_SIZE));
	}

	public boolean isMenuVisible() {
		return menuPanel.isVisible();
	}

	public TabPane getTabContentPane() {
		return tabPane;
	}

	public void setMenuVisible(boolean menuVisible) {
		// TODO drawer style
		menuPanel.setVisible(menuVisible);
	}
}
