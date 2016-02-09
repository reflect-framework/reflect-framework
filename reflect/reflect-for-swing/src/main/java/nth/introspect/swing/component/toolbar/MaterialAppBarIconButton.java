package nth.introspect.swing.component.toolbar;

import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;


public class MaterialAppBarIconButton extends MaterialButton {

	public MaterialAppBarIconButton(MaterialStyle materialStyle, FontAwesome fontAwesomeIcon) {
		super(materialStyle.appBar.icon.FOREGROUND1, materialStyle.appBar.icon.PRESSED, materialStyle.appBar.icon.PADDING, materialStyle.appBar.icon.SIZE, fontAwesomeIcon);
	}

	private static final long serialVersionUID = 5925859067046013332L;

}
