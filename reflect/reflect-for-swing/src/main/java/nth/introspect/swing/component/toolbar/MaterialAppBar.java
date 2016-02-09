package nth.introspect.swing.component.toolbar;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;
import nth.introspect.ui.swing.properygrid.SwingUtil;

public class MaterialAppBar extends JToolBar {

	private static final long serialVersionUID = 318218084707411312L;
	private final MaterialStyle materialStyle;

	public MaterialAppBar(UserInterfaceContainer userInterfaceContainer) {
		GraphicalUserinterfaceController controller=userInterfaceContainer.get(GraphicalUserinterfaceController.class);
		
		materialStyle = controller.getMaterialStyle();
		setFloatable(false);
		setPreferredSize(new Dimension(0, materialStyle.appBar.HEIGHT));
		setBackground(materialStyle.appBar.BACKGROUND);

		MaterialButton menuButton = new MaterialAppBarMenuButton(materialStyle);
		add(menuButton);
		
		JLabel title=new MaterialAppBarTitle(userInterfaceContainer);
		add(title);
		
		add(Box.createHorizontalGlue());
		
		
		MaterialButton minimizeButton = new MaterialAppBarMinimizeButton(materialStyle);
		add(minimizeButton);
		
		MaterialButton maximizeButton = new MaterialAppBarMaximizeButton(materialStyle);
		add(maximizeButton);
		
		MaterialButton closeButton = new MaterialAppBarCloseButton(materialStyle);
		add(closeButton);
	}

}
