package nth.introspect.ui.swing.view.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.MaterialColorTheme;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.swing.view.menu.item.ActionMethodItem;
import nth.introspect.ui.swing.view.menu.item.Item;
import nth.introspect.ui.swing.view.menu.item.ServiceObjectItem;

public class MenuListRenderer extends JLabel implements ListCellRenderer<Item> {

	private static final long serialVersionUID = -3154276898763785497L;
	private MaterialStyle materialStyle;

	public MenuListRenderer(MaterialStyle materialStyle) {
		this.materialStyle = materialStyle;
		setOpaque(true);
		setPreferredSize(new Dimension(0, materialStyle.list.singleLine.HEIGHT));

	}

	@Override
	public Component getListCellRendererComponent(javax.swing.JList<? extends Item> list,
			Item item, int index, boolean isSelected, boolean hasFocus) {
		if (item instanceof ServiceObjectItem) {
			ServiceObjectItem serviceObjectItem = (ServiceObjectItem) item;
			return getCellRendererComponent(serviceObjectItem, isSelected, hasFocus);
		} else {
			ActionMethodItem actionMethodItem = (ActionMethodItem) item;
			return getCellRendererComponent(actionMethodItem, isSelected, hasFocus);
		}
	}

	private Component getCellRendererComponent(ServiceObjectItem serviceObjectItem,
			boolean isSelected, boolean hasFocus) {
		// TODO Color separatorColor =
		MaterialColorTheme theme = MaterialColorTheme.defaultTheme;
		EmptyBorder padding = new EmptyBorder(0, materialStyle.list.singleLine.PADDING_LEFT, 0,
				materialStyle.list.singleLine.PADDING_RIGHT);
		setBorder(padding);

		setText(serviceObjectItem.getText());
		setToolTipText(serviceObjectItem.getDescription());
		setVisible(serviceObjectItem.isVisible());
		setEnabled(serviceObjectItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(materialStyle.list.singleLine.FONT.deriveFont(Font.BOLD));
		
		Color foreground=isSelected?theme.getAccent().getBackground():theme.getBackGround().getForeground1();
		setForeground(foreground);
		Color background=hasFocus?theme.getPrimaryLight().getBackground():theme.getBackGround().getBackground();
		setBackground(background);
		return this;
	};

	private Component getCellRendererComponent(ActionMethodItem actionMethodItem,
			boolean isSelected, boolean hasFocus) {
		setBorder(new EmptyBorder(0, materialStyle.list.singleLine.PADDING_LEFT
				+ materialStyle.list.INDENT, 0, materialStyle.list.singleLine.PADDING_RIGHT));

		MaterialColorTheme theme = MaterialColorTheme.defaultTheme;
		
		setText(actionMethodItem.getText());
		setToolTipText(actionMethodItem.getDescription());
		setVisible(actionMethodItem.isVisible());
		setEnabled(actionMethodItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(materialStyle.list.singleLine.FONT);

		Color foreground=isSelected?theme.getAccent().getBackground():theme.getBackGround().getForeground1();
		setForeground(foreground);
		Color background=hasFocus?theme.getPrimaryLight().getBackground():theme.getBackGround().getBackground();
		setBackground(background);
		return this;
	}
}
