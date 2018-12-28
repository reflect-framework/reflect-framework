package nth.reflect.fw.ui.swing.tab.form.proppanel;

import nth.reflect.fw.ui.swing.properygrid.WrapingLabel;

@SuppressWarnings("serial")
public class PropertyLabel extends WrapingLabel implements nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyLabel {

	@Override
	public void setDescription(String description) {
		super.setToolTipText(description);
	}

}
