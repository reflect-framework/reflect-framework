package nth.reflect.fw.ui.swing.view.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.component.GridStyle;
import nth.reflect.fw.ui.swing.util.AwtFontFactory;
import nth.reflect.fw.ui.swing.util.ColorFactory;
import nth.reflect.fw.ui.swing.view.menu.item.ActionMethodItem;
import nth.reflect.fw.ui.swing.view.menu.item.Item;
import nth.reflect.fw.ui.swing.view.menu.item.ServiceObjectItem;

public class MenuListRenderer extends JLabel implements ListCellRenderer<Item> {

	private static final long serialVersionUID = -3154276898763785497L;
	private final ReflectColors reflectColors;


	public MenuListRenderer(ReflectColors reflectColors) {
		this.reflectColors=reflectColors;
		setOpaque(true);
		setPreferredSize(new Dimension(0, GridStyle.getMinHeight()));
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
		EmptyBorder padding = new EmptyBorder(0, GridStyle.getPaddingLeft(), 0,
				GridStyle.getPaddingRight());
		setBorder(padding);

		setText(serviceObjectItem.getText());
		setToolTipText(serviceObjectItem.getDescription());
		setVisible(serviceObjectItem.isVisible());
		setEnabled(serviceObjectItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(AwtFontFactory.create(GridStyle.getCellFont()));

		Color red = Color.RED;//TODO
		Color foreground = isSelected ? red : ColorFactory.create(GridStyle.getTextColor(reflectColors));
		setForeground(foreground);
		Color background = hasFocus ? ColorFactory.create(GridStyle.getBackgroundHighLighted(reflectColors))
				: ColorFactory.create(GridStyle.getBackground(reflectColors));
		setBackground(background);
		return this;
	};

	private Component getCellRendererComponent(ActionMethodItem actionMethodItem,
			boolean isSelected, boolean hasFocus) {
		setBorder(new EmptyBorder(0,
				GridStyle.getPaddingLeft()
						+ GridStyle.getIndent(),
				0, GridStyle.getPaddingRight()));


		setText(actionMethodItem.getText());
		setToolTipText(actionMethodItem.getDescription());
		setVisible(actionMethodItem.isVisible());
		setEnabled(actionMethodItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(AwtFontFactory.create(GridStyle.getCellFont()));

		Color red = Color.RED;//TODO
		Color foreground = isSelected ? red : ColorFactory.create(GridStyle.getTextColor(reflectColors));
		setForeground(foreground);
		Color background = hasFocus ? ColorFactory.create(GridStyle.getBackgroundHighLighted(reflectColors))
				: ColorFactory.create(GridStyle.getBackground(reflectColors));
		setBackground(background);
		return this;
	}
}
