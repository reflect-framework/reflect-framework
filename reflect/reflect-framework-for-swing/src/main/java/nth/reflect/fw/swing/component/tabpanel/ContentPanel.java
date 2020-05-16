package nth.reflect.fw.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.TabsListener;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.swing.tab.Tab;
import nth.reflect.fw.swing.util.ColorFactory;

public class ContentPanel extends JPanel implements TabsListener<Tab> {

	private static final long serialVersionUID = 8212219054026076354L;

	public ContentPanel(UserInterfaceContainer userInterfaceContainer, Tabs<Tab> tabs) {
		tabs.addListener(this);
		ColorProvider colorProvider = userInterfaceContainer.get(ColorProvider.class);
		setBackground(ColorFactory.create(colorProvider.getContentColors().getBackground()));
		setLayout(new BorderLayout());
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		// do nothing
	}

	@Override
	public void onAddTab(Tab newTab) {
		// do nothing
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		removeAll();
		if (selectedTab != null) {
			add(selectedTab, BorderLayout.CENTER);
		}
		repaint();

	}
}
