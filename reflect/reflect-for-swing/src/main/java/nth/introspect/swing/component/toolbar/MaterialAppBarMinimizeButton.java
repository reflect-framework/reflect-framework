package nth.introspect.swing.component.toolbar;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;
import nth.introspect.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarMinimizeButton extends MaterialAppBarIconButton implements ActionListener{

	private static final long serialVersionUID = 864750663334885651L;

	public MaterialAppBarMinimizeButton(MaterialStyle materialStyle) {
		super(materialStyle, FontAwesome.fa_long_arrow_down);
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