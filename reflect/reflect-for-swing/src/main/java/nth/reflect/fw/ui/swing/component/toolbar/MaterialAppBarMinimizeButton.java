package nth.reflect.fw.ui.swing.component.toolbar;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarMinimizeButton extends MaterialAppBarIconButton implements ActionListener{

	private static final long serialVersionUID = 864750663334885651L;

	public MaterialAppBarMinimizeButton(MaterialStyle materialStyle) throws MalformedURLException {
		super(materialStyle, FontAwesomeUrl.LONG_ARROW_DOWN);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		Component mainWindow = SwingUtil.findParentComponentOfType((Component) component, JFrame.class);
		if (mainWindow!=null) {
			((JFrame)mainWindow).setState(Frame.ICONIFIED);
		}
	}
	
	
}