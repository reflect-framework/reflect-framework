package nth.introspect.swing.component.toolbar;

import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;

public class MaterialAppBarIconButton extends MaterialButton {

	private static final long serialVersionUID = 5925859067046013332L;

	public MaterialAppBarIconButton(MaterialStyle materialStyle, FontAwesome fontAwesomeIcon) {
		super(materialStyle.getApplicationToolbarIconStyle().getColor(),
				materialStyle.getApplicationToolbarIconStyle().getPressedColor(),
				materialStyle.getApplicationToolbarIconStyle().getPadding(),
				materialStyle.getApplicationToolbarIconStyle().getSize(), fontAwesomeIcon);
	}

}
