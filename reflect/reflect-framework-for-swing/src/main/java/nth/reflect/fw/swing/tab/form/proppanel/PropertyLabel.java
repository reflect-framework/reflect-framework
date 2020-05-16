package nth.reflect.fw.swing.tab.form.proppanel;

import nth.reflect.fw.swing.properygrid.WrapingLabel;

@SuppressWarnings("serial")
public class PropertyLabel extends WrapingLabel implements nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyLabel {

	@Override
	public void setDescription(String description) {
		super.setToolTipText(description);
	}

}
