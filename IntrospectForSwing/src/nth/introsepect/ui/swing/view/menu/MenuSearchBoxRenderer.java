package nth.introsepect.ui.swing.view.menu;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URI;
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
			URI iconURI = item.getIconURI();
			if (iconURI != null) {
				try {
					URL iconURL = iconURI.toURL();
					setIcon(new ImageIcon(iconURL));
				} catch (MalformedURLException e) {
				}
			}
			setText(item.getTextPath(MenuSearchBoxModel.SEPERATOR));
			setToolTipText(item.getDescription());
		}
		return this;
	}

}
