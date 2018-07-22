package nth.reflect.fw.ui.swing.view.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.style.control.ListStyle;
import nth.reflect.fw.ui.swing.util.AwtFontFactory;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.swing.view.menu.item.ActionMethodItem;
import nth.reflect.fw.ui.swing.view.menu.item.Item;
import nth.reflect.fw.ui.swing.view.menu.item.ServiceObjectItem;

public class MenuListRenderer extends JLabel implements ListCellRenderer<Item> {

	private static final long serialVersionUID = -3154276898763785497L;
	private ListStyle style;


	public MenuListRenderer(MaterialStyle materialStyle) {
		this.style= materialStyle.getListSingleLineStyle();
		setOpaque(true);
		setPreferredSize(new Dimension(0, materialStyle.getListSingleLineStyle().getMinHeight()));
	}

	@Override
	public Component getListCellRendererComponent(javax.swing.JList<? extends Item> list, Item item,
			int index, boolean isSelected, boolean hasFocus) {
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
		EmptyBorder padding = new EmptyBorder(0, style.getPaddingLeft(), 0,
				style.getPaddingRight());
		setBorder(padding);

		setText(serviceObjectItem.getText());
		setToolTipText(serviceObjectItem.getDescription());
		setVisible(serviceObjectItem.isVisible());
		setEnabled(serviceObjectItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(AwtFontFactory.create(style.getTitleFont()));

		Color red = Color.RED;//TODO
		Color foreground = isSelected ? red : ColorFactory.create(style.getTextColor());
		setForeground(foreground);
		Color lightGray = Color.LIGHT_GRAY;//TODO
		Color background = hasFocus ? lightGray
				: ColorFactory.create(style.getBackgroundColor());
		setBackground(background);
		return this;
	};

	private Component getCellRendererComponent(ActionMethodItem actionMethodItem,
			boolean isSelected, boolean hasFocus) {
		setBorder(new EmptyBorder(0,
				style.getPaddingLeft()
						+ style.getIndent(),
				0, style.getPaddingRight()));


		setText(actionMethodItem.getText());
		setToolTipText(actionMethodItem.getDescription());
		setVisible(actionMethodItem.isVisible());
		setEnabled(actionMethodItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(AwtFontFactory.create(style.getTitleFont()));

		Color red = Color.RED;//TODO
		Color foreground = isSelected ? red : ColorFactory.create(style.getTextColor());
		setForeground(foreground);
		Color lightGray = Color.LIGHT_GRAY;//TODO
		Color background = hasFocus ? lightGray
				: ColorFactory.create(style.getBackgroundColor());
		setBackground(background);
		return this;
	}
}
