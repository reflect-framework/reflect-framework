package nth.reflect.fw.ui.swing.component.tabpanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import nth.reflect.fw.gui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColors;
import nth.reflect.fw.ui.swing.tab.Tab;
import nth.reflect.fw.ui.swing.util.AwtFontFactory;
import nth.reflect.fw.ui.swing.util.ColorFactory;

public class MaterialTabBarButton extends JButton {

	private static final long serialVersionUID = 271479970687266003L;
	public ReflectColors reflectColors;

	public MaterialTabBarButton(Tabs<Tab> tabs, ReflectColors reflectColors, Tab tab, boolean selected) {
		setContentAreaFilled(false);
		setFocusable(false);
		addMouseListener(createBackgroundPainter(tabs, ColorFactory.create(ApplicationBarStyle.getBackgroundHighLighted(reflectColors)), tab));
//		 setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, style.COLORS.getForeground1()));
//		setBorder(BorderFactory.createBevelBorder(1));
//		setText(view.getViewTitle().toUpperCase());
		//putting everything in a label so we can use borders 
		JLabel label = new JLabel(tab.getDisplayName().toUpperCase());		
		EmptyBorder padding = new EmptyBorder(12,12,12,12);
		if (selected) {
			MatteBorder bottomBar = BorderFactory.createMatteBorder(0,  0, 2, 0,ColorFactory.create( reflectColors.getContentColors().getBackground()));
			label.setBorder(new CompoundBorder( bottomBar, padding));
		} else {
			label.setBorder(padding);
		}
		label.setFont(AwtFontFactory.create(MaterialFont.getRobotoMedium(12)));
		label.setForeground(ColorFactory.create(reflectColors.getPrimaryColors().getForeground()));
		add(label);
	}
	
	private MouseListener createBackgroundPainter(final Tabs<Tab> tabs, final Color heighLightColor, final Tab tab) {
		return new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOpaque(true);
				setBackground(heighLightColor);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setOpaque(false);
				setContentAreaFilled(false);
				tabs.setSelected(tab);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

	};
	}

}