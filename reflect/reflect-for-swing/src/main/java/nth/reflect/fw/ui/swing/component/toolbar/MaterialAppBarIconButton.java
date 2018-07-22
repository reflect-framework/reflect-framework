package nth.reflect.fw.ui.swing.component.toolbar;

import java.net.MalformedURLException;

import nth.reflect.fw.ui.style.MaterialStyle;
import nth.reflect.fw.ui.swing.component.button.MaterialButton;

public class MaterialAppBarIconButton extends MaterialButton {

	private static final long serialVersionUID = 5925859067046013332L;

	public MaterialAppBarIconButton(MaterialStyle materialStyle, String fontIconUrl) throws MalformedURLException {
		super(materialStyle.getApplicationToolbarIconStyle().getColor(),
				materialStyle.getApplicationToolbarIconStyle().getPressedColor(),
				materialStyle.getApplicationToolbarIconStyle().getPadding(),
				materialStyle.getApplicationToolbarIconStyle().getSize(), fontIconUrl);
	}

}
