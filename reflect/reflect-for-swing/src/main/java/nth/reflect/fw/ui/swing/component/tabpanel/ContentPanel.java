package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.swing.view.SwingView;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 8212219054026076354L;

	public ContentPanel(UserInterfaceContainer userInterfaceContainer) {
		ReflectColors reflectColors = ReflectColors.getFrom(userInterfaceContainer);
		setBackground(ColorFactory.create(reflectColors.getContentColors().getBackground()));
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
