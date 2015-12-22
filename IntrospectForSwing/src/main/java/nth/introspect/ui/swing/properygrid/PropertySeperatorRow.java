package nth.introspect.ui.swing.properygrid;

import java.awt.Dimension;

import javax.swing.JPanel;

import nth.introspect.ui.swing.style.ColorUtil;

public class PropertySeperatorRow extends JPanel {

	private static final long serialVersionUID = 1902884200557920802L;

	public PropertySeperatorRow() {
		setMaximumSize(new Dimension(Integer.MAX_VALUE,1));
		setBackground(ColorUtil.getMiddleColor());
	}
}
