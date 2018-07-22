package nth.reflect.fw.ui.swing.component.tabpanel;

import java.net.MalformedURLException;

import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;

public class MaterialTabBarContextMenuButton extends MaterialButton {

	private static final long serialVersionUID = -1862078530902019839L;

	public MaterialTabBarContextMenuButton(MaterialStyle materialStyle) throws MalformedURLException {
		super(materialStyle.getTabToolbarIconStyle().getColor(), materialStyle.getTabToolbarIconStyle().getPressedColor(), materialStyle.getTabToolbarIconStyle().getPadding(), materialStyle.getTabToolbarIconStyle().getSize(), FontAwesomeUrl.ELLIPSIS_V);
	}

}
