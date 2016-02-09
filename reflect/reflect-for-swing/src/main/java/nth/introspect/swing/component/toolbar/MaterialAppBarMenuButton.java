package nth.introspect.swing.component.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;
import nth.introspect.ui.swing.mainwindow.MainWindow;
import nth.introspect.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarMenuButton extends MaterialAppBarIconButton implements ActionListener {

	private static final long serialVersionUID = -1031306949702539790L;

	public MaterialAppBarMenuButton(MaterialStyle materialStyle) {
		super(materialStyle, FontAwesome.fa_navicon);
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
