package nth.introspect.swing.component.tabpanel;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JToolBar;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.swing.util.ColorFactory;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.swing.view.SwingView;

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