package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JToolBar;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.component.ApplicationBarStyle;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.swing.view.SwingView;

public class MaterialTabBar extends JToolBar {

	private static final long serialVersionUID = 318218084707411312L;
	private ReflectColors reflectColors;
	private SwingViewContainer2 viewContainer;

	public MaterialTabBar(UserInterfaceContainer userInterfaceContainer, SwingViewContainer2 viewContainer) {
		this.viewContainer = viewContainer;
		reflectColors = ReflectColors.getFrom(userInterfaceContainer);
		setFloatable(false);
		setPreferredSize(new Dimension(0, 56));
		setBackground(ColorFactory.create(ApplicationBarStyle.getBackGround(reflectColors)));
	}

	void update(List<SwingView> views, View selectedView) throws MalformedURLException {
		removeAll();
		
		for (SwingView view : views) {
			MaterialTabBarButton tabButton = new MaterialTabBarButton(viewContainer, reflectColors, view, selectedView==view);
			add(tabButton);
		}

		add(Box.createHorizontalGlue());

		MaterialButton minimizeButton = new MaterialTabBarContextMenuButton(reflectColors);
		add(minimizeButton);
		repaint();
	}

	
}