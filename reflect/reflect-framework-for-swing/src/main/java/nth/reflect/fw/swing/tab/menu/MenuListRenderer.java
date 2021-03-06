package nth.reflect.fw.swing.tab.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import nth.reflect.fw.gui.component.table.TableStyle;
import nth.reflect.fw.gui.item.method.MethodOwnerItem;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.swing.util.AwtFontFactory;
import nth.reflect.fw.swing.util.ColorFactory;

public class MenuListRenderer extends JLabel implements ListCellRenderer<Item> {

	private static final long serialVersionUID = -3154276898763785497L;
	private final ColorProvider colorProvider;


	public MenuListRenderer(ColorProvider colorProvider) {
		this.colorProvider=colorProvider;
		setOpaque(true);
		setPreferredSize(new Dimension(0, TableStyle.getMinHeight()));
	}

	@Override
	public Component getListCellRendererComponent(javax.swing.JList<? extends Item> list, Item item,
			int index, boolean isSelected, boolean hasFocus) {
		if (item instanceof MethodOwnerItem) {
			MethodOwnerItem serviceObjectItem = (MethodOwnerItem) item;
			return getCellRendererComponent(serviceObjectItem, isSelected, hasFocus);
		} else {
			Item actionMethodItem = (Item) item;
			return getCellRendererComponent(actionMethodItem, isSelected, hasFocus);
		}
	}

	private Component getCellRendererComponent(MethodOwnerItem serviceObjectItem,
			boolean isSelected, boolean hasFocus) {
		// TODO Color separatorColor =
		EmptyBorder padding = new EmptyBorder(0, TableStyle.getPaddingLeft(), 0,
				TableStyle.getPaddingRight());
		setBorder(padding);

		setText(serviceObjectItem.getText());
		setToolTipText(serviceObjectItem.getDescription());
		setVisible(serviceObjectItem.isVisible());
		setEnabled(serviceObjectItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(AwtFontFactory.create(TableStyle.getCellFont()));

		Color red = Color.RED;//TODO
		Color foreground = isSelected ? red : ColorFactory.create(TableStyle.getTextColor(colorProvider));
		setForeground(foreground);
		Color background = hasFocus ? ColorFactory.create(TableStyle.getBackgroundHighLighted(colorProvider))
				: ColorFactory.create(TableStyle.getBackground(colorProvider));
		setBackground(background);
		return this;
	};

	private Component getCellRendererComponent(Item actionMethodItem,
			boolean isSelected, boolean hasFocus) {
		setBorder(new EmptyBorder(0,
				TableStyle.getPaddingLeft()
						+ TableStyle.getIndent(),
				0, TableStyle.getPaddingRight()));


		setText(actionMethodItem.getText());
		setToolTipText(actionMethodItem.getDescription());
		setVisible(actionMethodItem.isVisible());
		setEnabled(actionMethodItem.isEnabled());
		// setIcon(entry.getImage());

		setFont(AwtFontFactory.create(TableStyle.getCellFont()));

		Color red = Color.RED;//TODO
		Color foreground = isSelected ? red : ColorFactory.create(TableStyle.getTextColor(colorProvider));
		setForeground(foreground);
		Color background = hasFocus ? ColorFactory.create(TableStyle.getBackgroundHighLighted(colorProvider))
				: ColorFactory.create(TableStyle.getBackground(colorProvider));
		setBackground(background);
		return this;
	}
}
