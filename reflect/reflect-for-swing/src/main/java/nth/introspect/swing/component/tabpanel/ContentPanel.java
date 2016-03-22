package nth.introspect.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.swing.util.ColorFactory;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.swing.view.SwingView;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 8212219054026076354L;

	public ContentPanel(UserInterfaceContainer userInterfaceContainer) {
		GraphicalUserinterfaceController controller = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		MaterialStyle materialStyle = controller.getMaterialStyle();
		setBackground(ColorFactory.create(materialStyle.getTabContainerStyle().getBackground()));
		setLayout(new BorderLayout());
	}

	public void setView(SwingView currentView) {
		removeAll();
		if (currentView != null) {
			add(currentView, BorderLayout.CENTER);
		}
		repaint();
	}
}
