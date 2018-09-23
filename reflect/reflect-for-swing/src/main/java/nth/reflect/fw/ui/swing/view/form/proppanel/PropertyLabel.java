package nth.reflect.fw.ui.swing.view.form.proppanel;

import nth.reflect.fw.ui.swing.properygrid.WrapingLabel;

@SuppressWarnings("serial")
public class PropertyLabel extends WrapingLabel implements nth.reflect.fw.ui.view.form.propertypanel.PropertyLabel {

	@Override
	public void setDescription(String description) {
		super.setToolTipText(description);
	}

}
