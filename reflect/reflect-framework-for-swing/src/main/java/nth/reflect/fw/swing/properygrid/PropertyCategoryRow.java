package nth.reflect.fw.swing.properygrid;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import nth.reflect.fw.swing.style.ColorUtil;

public class PropertyCategoryRow extends JPanel {

	private static final long serialVersionUID = -6750529615073737210L;

	public PropertyCategoryRow(String text) {
		setBackground(ColorUtil.getMediumDarkColor());
		WrapingLabel label = new WrapingLabel(text);
		label.setForeground(ColorUtil.getDark());
		label.setAlignmentX(SwingConstants.LEFT);
		setPreferredSize(label.getPreferredSize());//set HEIGHT to label HEIGHT
		add(label);
	}

}
