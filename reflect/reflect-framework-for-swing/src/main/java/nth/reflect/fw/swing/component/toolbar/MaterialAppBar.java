package nth.reflect.fw.swing.component.toolbar;

import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.swing.component.button.MaterialButton;
import nth.reflect.fw.swing.util.ColorFactory;

public class MaterialAppBar extends JToolBar {

	private static final long serialVersionUID = 318218084707411312L;
	private final ColorProvider colorProvider;

	public MaterialAppBar(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		colorProvider = userInterfaceContainer.get(ColorProvider.class);
		setFloatable(false);
		setPreferredSize(new Dimension(0, ApplicationBarStyle.getHeightInPixels()));
		setBackground(ColorFactory.create(ApplicationBarStyle.getBackGround(colorProvider)));

		MaterialButton menuButton = new MaterialAppBarMenuButton(colorProvider);
		add(menuButton);
		
		JLabel title=new MaterialAppBarTitle(userInterfaceContainer);
		add(title);
		
		add(Box.createHorizontalGlue());
	}

}
