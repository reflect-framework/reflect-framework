package nth.reflect.fw.ui.swing.view.container;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
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

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.item.tab.TabsItem;
import nth.reflect.fw.ui.swing.item.popupmenu.PopupMenu;

/**
 * Component to be used as tabComponent; Contains a JLabel to show the text and
 * a JButton to close the tab it belongs to
 */
@SuppressWarnings("serial")
public class TabHeader extends JPanel {
	private final SwingViewContainer swingViewContainer;
	private final Component tab;
	public final LanguageProvider languageProvider;

	public TabHeader( ViewContainer<?> viewContainer, LanguageProvider languageProvider,
			final Component tab, final String title, final String description,
			final Icon icon) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.languageProvider = languageProvider;
		this.swingViewContainer = (SwingViewContainer) viewContainer;

		if (tab == null) {
			throw new NullPointerException("View is null");
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
					swingViewContainer.setSelectedComponent(tab);
					break;
				case MouseEvent.BUTTON3:
					showPopupMenu(e.getX(), e.getY());
					break;
				}

			}
		};
	}

	private class TabButton extends JButton implements ActionListener {
		public TabButton() {
			int size = 18;
			setPreferredSize(new Dimension(size, size));
			setToolTipText(languageProvider.getText("Close this tab"));
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

		public void actionPerformed(ActionEvent e) {
			int i = swingViewContainer.indexOfTabComponent(TabHeader.this);
			if (i != -1) {
				swingViewContainer.remove(i);
			}
		}

		// we don't want to update UI for this button
		public void updateUI() {
		}

		// paint the cross
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
			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
					- delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
					- delta - 1);
			g2.dispose();
		}
	}

	public void showPopupMenu(int x, int y) {
		TabsItem tabsItem = new TabsItem(languageProvider, swingViewContainer,
				(View) tab);
		List<Item> items = tabsItem.getChildren();
		PopupMenu popupMenu = new PopupMenu(items);
		popupMenu.show(this, x, y);

	}

}
