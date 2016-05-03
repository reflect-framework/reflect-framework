package nth.introspect.swing.component.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.introspect.ui.swing.mainwindow.MainWindow;
import nth.introspect.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarMenuButton extends MaterialAppBarIconButton implements ActionListener {

	private static final long serialVersionUID = -1031306949702539790L;

	public MaterialAppBarMenuButton(MaterialStyle materialStyle) throws MalformedURLException {
		super(materialStyle, FontAwesomeUrl.NAVICON);
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
