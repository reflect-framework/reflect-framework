package nth.introspect.swing.component.toolbar;

import java.net.MalformedURLException;

import nth.introspect.swing.component.button.MaterialButton;
import nth.introspect.ui.style.MaterialStyle;

public class MaterialAppBarIconButton extends MaterialButton {

	private static final long serialVersionUID = 5925859067046013332L;

	public MaterialAppBarIconButton(MaterialStyle materialStyle, String fontIconUrl) throws MalformedURLException {
		super(materialStyle.getApplicationToolbarIconStyle().getColor(),
				materialStyle.getApplicationToolbarIconStyle().getPressedColor(),
				materialStyle.getApplicationToolbarIconStyle().getPadding(),
				materialStyle.getApplicationToolbarIconStyle().getSize(), fontIconUrl);
	}

}
