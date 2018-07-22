package nth.reflect.fw.ui.swing.component.toolbar;

import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;
import nth.reflect.fw.ui.swing.util.ColorFactory;

public class MaterialAppBar extends JToolBar {

	private static final long serialVersionUID = 318218084707411312L;
	private final MaterialStyle materialStyle;

	public MaterialAppBar(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		GraphicalUserinterfaceController<?> controller=userInterfaceContainer.get(GraphicalUserinterfaceController.class);
		
		materialStyle = controller.getMaterialStyle();
		setFloatable(false);
		setPreferredSize(new Dimension(0, materialStyle.getApplicationToolbarStyle().getHeight()));
		setBackground(ColorFactory.create(materialStyle.getApplicationToolbarStyle().getBackgroundColor()));

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
