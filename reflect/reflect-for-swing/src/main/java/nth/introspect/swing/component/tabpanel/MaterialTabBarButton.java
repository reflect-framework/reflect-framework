package nth.introspect.swing.component.tabpanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import nth.introspect.swing.util.AwtFontFactory;
import nth.introspect.swing.util.ColorFactory;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.swing.view.SwingView;

public class MaterialTabBarButton extends JButton {

	private static final long serialVersionUID = 271479970687266003L;
	public MaterialStyle materialStyle;

	public MaterialTabBarButton(SwingViewContainer2 viewContainer, MaterialStyle materialStyle, SwingView view, boolean selected) {
		setContentAreaFilled(false);
		setFocusable(false);
		addMouseListener(createBackgroundPainter(viewContainer, ColorFactory.create(materialStyle.getTabToolbarStyle().getPressedColor()), view));
//		 setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, style.COLORS.getForeground1()));
//		setBorder(BorderFactory.createBevelBorder(1));
//		setText(view.getViewTitle().toUpperCase());
		//putting everything in a label so we can use borders 
		JLabel label = new JLabel(view.getViewTitle().toUpperCase());		
		EmptyBorder padding = new EmptyBorder(12,12,12,12);
		if (selected) {
			MatteBorder bottomBar = BorderFactory.createMatteBorder(0,  0, 2, 0,ColorFactory.create( materialStyle.getTabContainerStyle().getBackground()));
			label.setBorder(new CompoundBorder( bottomBar, padding));
		} else {
			label.setBorder(padding);
		}
		label.setFont(AwtFontFactory.create(materialStyle.getTabToolbarButtonStyle().getFont()));
		label.setForeground(ColorFactory.create(materialStyle.getTabToolbarButtonStyle().getTextColor()));
		add(label);
	}
	
	private MouseListener createBackgroundPainter(final SwingViewContainer2 viewContainer, final Color heighLightColor, final SwingView view) {
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
				viewContainer.setSelectedView(view);
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