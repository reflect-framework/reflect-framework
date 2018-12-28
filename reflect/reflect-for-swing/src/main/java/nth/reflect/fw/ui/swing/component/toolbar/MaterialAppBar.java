package nth.reflect.fw.ui.swing.component.toolbar;

import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;
import nth.reflect.fw.ui.swing.util.ColorFactory;

public class MaterialAppBar extends JToolBar {

	private static final long serialVersionUID = 318218084707411312L;
	private final ReflectColors reflectColors;

	public MaterialAppBar(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		reflectColors = ReflectColors.getFrom(userInterfaceContainer);
		setFloatable(false);
		setPreferredSize(new Dimension(0, ApplicationBarStyle.getHeightInPixels()));
		setBackground(ColorFactory.create(ApplicationBarStyle.getBackGround(reflectColors)));

		MaterialButton menuButton = new MaterialAppBarMenuButton(reflectColors);
		add(menuButton);
		
		JLabel title=new MaterialAppBarTitle(userInterfaceContainer);
		add(title);
		
		add(Box.createHorizontalGlue());
	}

}
