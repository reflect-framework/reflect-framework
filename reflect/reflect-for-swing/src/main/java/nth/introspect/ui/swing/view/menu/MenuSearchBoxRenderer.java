package nth.introspect.ui.swing.view.menu;

import java.awt.Component;
import java.net.URL;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import nth.introspect.ui.item.HierarchicalItem;

@SuppressWarnings("serial")
public class MenuSearchBoxRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		
		
		if (value instanceof HierarchicalItem) {
			HierarchicalItem item = (HierarchicalItem) value;
			URL iconURL = item.getIconURL();
			if (iconURL != null) {
					setIcon(new ImageIcon(iconURL));
			}
			setText(item.getTextPath(MenuSearchBoxModel.SEPERATOR));
			setToolTipText(item.getDescription());
		}
		return this;
	}

}
