package nth.reflect.fw.swing.tab.header;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.item.tab.TabsItem;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.swing.item.popupmenu.PopupMenu;
import nth.reflect.fw.swing.tab.Tab;

/**
 * Component to be used as tabComponent; Contains a JLabel to show the text and
 * a JButton to close the tab it belongs to
 */
@SuppressWarnings("serial")
public class TabHeader extends JPanel {
	private final Tab tab;
	public final LanguageProvider languageProvider;
	private final Tabs<Tab> tabs;

	public TabHeader(Tabs<Tab> tabs, LanguageProvider languageProvider, Tab tab, String title, String description,
			Icon icon) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.tabs = tabs;
		this.languageProvider = languageProvider;

		if (tab == null) {
			throw new NullPointerException("Tab is null");
		}

		addMouseListener(createMouseListener());

		this.tab = tab;

		setOpaque(false);

		setToolTipText(description);
		// make JLabel read titles from JTabbedPane
		JLabel label = new JLabel() {
			@Override
			public Icon getIcon() {
				return icon;
			}

			@Override
			public String getText() {
				return title;
			}

			@Override
			public String getToolTipText() {
				return description;
			}
		};

		add(label);
		// add more space between the label and the button
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		// tab button
		JButton button = new TabButton();
		add(button);
		// add more space to the top of the component
		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	}

	public MouseListener createMouseListener() {
		return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1:
					tabs.setSelected(tab);
					break;
				case MouseEvent.BUTTON3:
					showPopupMenu(e.getX(), e.getY());
					break;
				}

			}
		};
	}

	private class TabButton extends JButton implements ActionListener {
		private final TranslatableString CLOSE_THIS_TAB = new TranslatableString(
				TabHeader.class.getCanonicalName() + ".close.this.tab", "Close this tab");

		public TabButton() {
			int size = 18;
			setPreferredSize(new Dimension(size, size));
			setToolTipText(languageProvider.getText(CLOSE_THIS_TAB));
			// Make the button looks the same for all Laf's
			setUI(new BasicButtonUI());
			// Make it transparent
			setContentAreaFilled(false);
			// No need to be focusable
			setFocusable(false);
			// Making nice rollover effect
			// we use the same listener for all buttons
			setRolloverEnabled(true);
			// Close the proper tab by clicking the button
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tabs.remove(tab);
		}

		// we don't want to update UI for this button
		@Override
		public void updateUI() {
		}

		// paint the cross
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			// shift the image for pressed buttons
			if (getModel().isPressed()) {
				g2.translate(1, 1);
			}
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.BLACK);
			if (getModel().isRollover()) {// TODO hide all buttons unless mouse
											// hovers or is active tab
				g2.setColor(Color.MAGENTA);
			}
			int delta = 6;
			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
			g2.dispose();
		}
	}

	public void showPopupMenu(int x, int y) {
		TabsItem<Tab> tabsItem = new TabsItem<>(languageProvider, tabs, tab);
		List<Item> items = tabsItem.getChildren();
		PopupMenu popupMenu = new PopupMenu(items);
		popupMenu.show(this, x, y);

	}

}
