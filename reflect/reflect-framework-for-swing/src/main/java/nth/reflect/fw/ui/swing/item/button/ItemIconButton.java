package nth.reflect.fw.ui.swing.item.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.swing.icon.IconFactory;
import nth.reflect.fw.ui.swing.style.SwingStyleConstant;

public class ItemIconButton extends JButton {

	private static final long serialVersionUID = 978201731374527080L;
	private ActionListener action;

	public ItemIconButton(final Item item) {
		// add
		// DomainPrivider.addPropertyChangeListener(serviceObject,methodInfo)???
		// to set enabled state?

		setIcon(IconFactory.create(item.getIconURL(),
				SwingStyleConstant.ICON_SIZE));
		
		action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				item.getAction().run();
			}
		};

		addActionListener(action);
		setToolTipText(item.getDescription());

	}
	
	
}
