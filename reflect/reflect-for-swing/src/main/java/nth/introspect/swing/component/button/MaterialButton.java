package nth.introspect.swing.component.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import nth.introspect.swing.util.ColorFactory;
import nth.introspect.ui.style.basic.Color;
import nth.introspect.ui.swing.icon.FontAwesomeIcon;

public class MaterialButton extends JButton {

	private static final long serialVersionUID = 9201185700452373792L;

	public MaterialButton(Color foregroundColor, Color heighlightColor, int padding, int iconSize, String fontIconUrl) throws MalformedURLException {
		super();
		setContentAreaFilled(false);
		setFocusable(false);
		setBorder(new EmptyBorder(padding,padding,padding,padding));
		FontAwesomeIcon icon = new FontAwesomeIcon(fontIconUrl);
		icon.setColor(ColorFactory.create(foregroundColor));
		icon.setSize(iconSize);
		setIcon(icon);
		addMouseListener(createBackgroundPainter(heighlightColor));
	}

	private MouseListener createBackgroundPainter(final Color heighlightColor) {
		return new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOpaque(true);
				setBackground(ColorFactory.create(heighlightColor));
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
