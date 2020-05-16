package nth.reflect.fw.swing.component.tabpanel;

import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.Box;
import javax.swing.JToolBar;

import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.TabsListener;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.swing.tab.Tab;
import nth.reflect.fw.swing.util.ColorFactory;

public class MaterialTabBar extends JToolBar implements TabsListener<Tab> {

	private static final long serialVersionUID = 318218084707411312L;
	private ColorProvider colorProvider;
	private final Tabs<Tab> tabs;

	public MaterialTabBar(UserInterfaceContainer userInterfaceContainer, Tabs<Tab> tabs) {
		this.tabs = tabs;
		tabs.addListener(this);
		colorProvider = userInterfaceContainer.get(ColorProvider.class);
		setFloatable(false);
		setPreferredSize(new Dimension(0, 56));
		setBackground(ColorFactory.create(ApplicationBarStyle.getBackGround(colorProvider)));
	}

	void update() {
		removeAll();
		Tab selectedTab = tabs.getSelected();
		for (Tab tab : tabs) {
			MaterialTabBarButton tabButton = new MaterialTabBarButton(tabs, colorProvider, tab, selectedTab == tab);
			add(tabButton);
		}

		add(Box.createHorizontalGlue());

		try {
			MaterialTabBarContextMenuButton minimizeButton = new MaterialTabBarContextMenuButton(colorProvider);
			add(minimizeButton);
		} catch (MalformedURLException e) {
		}

		repaint();
	}


	@Override
	public void onRemoveTab(Tab removedTab) {
		update();
	}

	@Override
	public void onAddTab(Tab newTab) {
		update();
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		update();
	}

}