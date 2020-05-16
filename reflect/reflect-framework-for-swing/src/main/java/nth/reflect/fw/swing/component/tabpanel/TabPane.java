package nth.reflect.fw.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.swing.tab.Tab;

public class TabPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private MaterialTabBar tabBar;
	private ContentPanel contentPanel;

	public TabPane(UserInterfaceContainer userInterfaceContainer, Tabs<Tab> tabs) {
		super(new BorderLayout());
		
		tabBar = new MaterialTabBar(userInterfaceContainer, tabs);
		contentPanel = new ContentPanel(userInterfaceContainer, tabs);
		add(tabBar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}
}
