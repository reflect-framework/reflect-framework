package nth.introspect.swing.component.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;
import nth.introspect.ui.swing.properygrid.SwingUtil;

public class MaterialAppBarCloseButton extends MaterialAppBarIconButton implements ActionListener{

	private static final long serialVersionUID = 864750663334885651L;
	public MaterialStyle materialStyle;

	public MaterialAppBarCloseButton(MaterialStyle materialStyle) {
		super(materialStyle, FontAwesome.fa_remove);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		Component found = SwingUtil.findParentComponentOfType((Component) component, JFrame.class);
		if (found!=null) {
			JFrame mainWindow=(JFrame) found;
			mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
		}

	}
	
	
}