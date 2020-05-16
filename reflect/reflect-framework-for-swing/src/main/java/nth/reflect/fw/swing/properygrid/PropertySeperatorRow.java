package nth.reflect.fw.swing.properygrid;

import java.awt.Dimension;

import javax.swing.JPanel;

import nth.reflect.fw.swing.style.ColorUtil;

public class PropertySeperatorRow extends JPanel {

	private static final long serialVersionUID = 1902884200557920802L;

	public PropertySeperatorRow() {
		setMaximumSize(new Dimension(Integer.MAX_VALUE,1));
		setBackground(ColorUtil.getMiddleColor());
	}
}
