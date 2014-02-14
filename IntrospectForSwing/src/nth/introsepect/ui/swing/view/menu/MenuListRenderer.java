package nth.introsepect.ui.swing.view.menu;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import nth.introsepect.ui.swing.icon.IconFactory;
import nth.introsepect.ui.swing.style.SwingStyleConstant;
import nth.introspect.ui.item.Item;

@SuppressWarnings("serial")
public class MenuListRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		
		
		if (value instanceof Item) {
			Item item = (Item) value;
			
			setText(item.getText());
			setToolTipText(item.getDescription());
			ImageIcon icon = IconFactory.create(item.getIconURI(), SwingStyleConstant.ICON_SIZE);
			setIcon(icon);
			
		} 
		return this;
	}

}
