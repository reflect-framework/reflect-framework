package nth.introspect.swing.component.tabpanel;

import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;

public class MaterialTabBarContextMenuButton extends MaterialButton {

	private static final long serialVersionUID = -1862078530902019839L;

	public MaterialTabBarContextMenuButton(MaterialStyle materialStyle) {
		super(materialStyle.getTabToolbarIconStyle().getColor(), materialStyle.getTabToolbarIconStyle().getPressedColor(), materialStyle.getTabToolbarIconStyle().getPadding(), materialStyle.getTabToolbarIconStyle().getSize(), FontAwesome.fa_ellipsis_v);
	}

}
