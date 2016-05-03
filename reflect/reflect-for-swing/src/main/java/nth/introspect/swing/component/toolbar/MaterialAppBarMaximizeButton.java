package nth.introspect.swing.component.toolbar;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.introspect.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarMaximizeButton extends MaterialAppBarIconButton implements ActionListener {

	private static final long serialVersionUID = 864750663334885651L;
	public MaterialStyle materialStyle;

	public MaterialAppBarMaximizeButton(MaterialStyle materialStyle) throws MalformedURLException {
		super(materialStyle, FontAwesomeUrl.COMPRESS);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		Component found = SwingUtil.findParentComponentOfType((Component) component, JFrame.class);
		if (found != null) {
			JFrame mainWindow = (JFrame) found;
			if (mainWindow.getExtendedState() == Frame.NORMAL) {
				mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
				// TODO change icon
			} else {
				mainWindow.setExtendedState(Frame.NORMAL);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				mainWindow.setSize(new Dimension(screenSize.width / 2, screenSize.height / 2));

				// TODO change icon
				// FIXME no titlebar but with resizing

			}
		}

	}

}