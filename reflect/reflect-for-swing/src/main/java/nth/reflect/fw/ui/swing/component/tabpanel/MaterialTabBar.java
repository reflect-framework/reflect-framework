package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JToolBar;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.swing.view.SwingView;

public class MaterialTabBar extends JToolBar {

	private static final long serialVersionUID = 318218084707411312L;
	private MaterialStyle materialStyle;
	private SwingViewContainer2 viewContainer;

	public MaterialTabBar(UserInterfaceContainer userInterfaceContainer, SwingViewContainer2 viewContainer) {
		this.viewContainer = viewContainer;
		GraphicalUserinterfaceController controller = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);

		materialStyle = controller.getMaterialStyle();
		setFloatable(false);
		setPreferredSize(new Dimension(0, materialStyle.getTabToolbarStyle().getHeight()));
		setBackground(ColorFactory.create(materialStyle.getTabToolbarStyle().getBackGroundColor()));
	}

	void update(List<SwingView> views, View selectedView) throws MalformedURLException {
		removeAll();
		
		for (SwingView view : views) {
			MaterialTabBarButton tabButton = new MaterialTabBarButton(viewContainer, materialStyle, view, selectedView==view);
			add(tabButton);
		}

		add(Box.createHorizontalGlue());

		MaterialButton minimizeButton = new MaterialTabBarContextMenuButton(materialStyle);
		add(minimizeButton);
		repaint();
	}

	
}