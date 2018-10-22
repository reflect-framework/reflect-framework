package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JToolBar;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.component.ApplicationBarStyle;
import nth.reflect.fw.ui.swing.tab.Tab;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;

public class MaterialTabBar extends JToolBar implements TabsListener<Tab> {

	private static final long serialVersionUID = 318218084707411312L;
	private ReflectColors reflectColors;
	private final Tabs<Tab> tabs;

	public MaterialTabBar(UserInterfaceContainer userInterfaceContainer, Tabs<Tab> tabs) {
		this.tabs = tabs;
		tabs.addListener(this);
		reflectColors = ReflectColors.getFrom(userInterfaceContainer);
		setFloatable(false);
		setPreferredSize(new Dimension(0, 56));
		setBackground(ColorFactory.create(ApplicationBarStyle.getBackGround(reflectColors)));
	}

	void update() {
		removeAll();
		Tab selectedTab = tabs.getSelected();
		for (Tab tab : tabs) {
			MaterialTabBarButton tabButton = new MaterialTabBarButton(tabs, reflectColors, tab, selectedTab == tab);
			add(tabButton);
		}

		add(Box.createHorizontalGlue());

		try {
			MaterialTabBarContextMenuButton minimizeButton = new MaterialTabBarContextMenuButton(reflectColors);
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