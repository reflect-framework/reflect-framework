package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.component.tab.Tabs;
import nth.reflect.fw.ui.swing.tab.Tab;

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
