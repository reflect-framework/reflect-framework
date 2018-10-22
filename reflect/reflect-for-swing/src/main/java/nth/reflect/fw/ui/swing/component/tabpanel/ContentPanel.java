package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.swing.tab.Tab;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;

public class ContentPanel extends JPanel implements TabsListener<Tab> {

	private static final long serialVersionUID = 8212219054026076354L;

	public ContentPanel(UserInterfaceContainer userInterfaceContainer, Tabs<Tab> tabs) {
		tabs.addListener(this);
		ReflectColors reflectColors = ReflectColors.getFrom(userInterfaceContainer);
		setBackground(ColorFactory.create(reflectColors.getContentColors().getBackground()));
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
