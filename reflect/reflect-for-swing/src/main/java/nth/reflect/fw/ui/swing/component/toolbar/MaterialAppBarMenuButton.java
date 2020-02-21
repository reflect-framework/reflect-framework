package nth.reflect.fw.ui.swing.component.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.swing.mainwindow.MainWindow;
import nth.reflect.fw.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarMenuButton extends MaterialAppBarIconButton implements ActionListener {

	private static final long serialVersionUID = -1031306949702539790L;

	public MaterialAppBarMenuButton(ColorProvider colorProvider) throws MalformedURLException {
		super(colorProvider, FontAwesomeUrl.NAVICON);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source!=null) {
			MainWindow mainWindow= SwingUtil.findParentComponentOfType((Component) source, MainWindow.class);
			boolean menuVisible = !mainWindow.isMenuVisible();
			mainWindow.setMenuVisible(menuVisible);
		}
		
	}

}
