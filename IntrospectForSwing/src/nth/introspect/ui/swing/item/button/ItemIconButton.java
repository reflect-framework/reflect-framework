package nth.introspect.ui.swing.item.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.swing.icon.IconFactory;
import nth.introspect.ui.swing.style.SwingStyleConstant;

public class ItemIconButton extends JButton {

	private static final long serialVersionUID = 978201731374527080L;
	private ActionListener action;

	public ItemIconButton(final Item item) {
		// add
		// DomainPrivider.addPropertyChangeListener(serviceObject,methodInfo)???
		// to set enabled state?

		setAccelerator(item.getHotKey());
		
		setIcon(IconFactory.create(item.getIconURI(),
				SwingStyleConstant.ICON_SIZE));
		
		action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				item.getAction().run();
			}
		};

		KeyStroke hotKey = item.getHotKey();
		setAccelerator(hotKey);
		
		addActionListener(action);
		setToolTipText(item.getDescription());

	}
	
	
	public void setAccelerator(KeyStroke keyStroke) {
		if (keyStroke != null) {
			super.registerKeyboardAction(action, keyStroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
		}
	}
}
