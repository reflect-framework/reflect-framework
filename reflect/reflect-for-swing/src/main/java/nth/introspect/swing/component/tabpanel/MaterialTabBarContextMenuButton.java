package nth.introspect.swing.component.tabpanel;

import java.net.MalformedURLException;

import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

public class MaterialTabBarContextMenuButton extends MaterialButton {

	private static final long serialVersionUID = -1862078530902019839L;

	public MaterialTabBarContextMenuButton(MaterialStyle materialStyle) throws MalformedURLException {
		super(materialStyle.getTabToolbarIconStyle().getColor(), materialStyle.getTabToolbarIconStyle().getPressedColor(), materialStyle.getTabToolbarIconStyle().getPadding(), materialStyle.getTabToolbarIconStyle().getSize(), FontAwesomeUrl.ELLIPSIS_V);
	}

}
