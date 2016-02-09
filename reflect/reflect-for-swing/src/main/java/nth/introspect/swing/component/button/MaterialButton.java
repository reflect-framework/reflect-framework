package nth.introspect.swing.component.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.MaterialContentColors;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;
import nth.introspect.ui.style.fontawesome.FontAwesomeIcon;

public class MaterialButton extends JButton {

	private static final long serialVersionUID = 9201185700452373792L;

	public MaterialButton(Color foregroundColor, Color pressedColor, int padding, int iconSize, FontAwesome fontAwesomeIcon) {
		super();
		setContentAreaFilled(false);
		setFocusable(false);
		setBorder(new EmptyBorder(padding,padding,padding,padding));
		FontAwesomeIcon icon = new FontAwesomeIcon(fontAwesomeIcon);
		icon.setColor(foregroundColor);
		icon.setSize(iconSize);
		setIcon(icon);
		addMouseListener(createBackgroundPainter(pressedColor));
	}

	private MouseListener createBackgroundPainter(final Color pressedColor) {
		return new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOpaque(true);
				setBackground(pressedColor);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setOpaque(false);
				setContentAreaFilled(false);
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
