package nth.reflect.fw.ui.tab.form.propertypanel;

import nth.reflect.fw.ui.style.component.PropertyPanelStyle;
import nth.reflect.fw.ui.tab.form.FormTab;

public enum PropertyFieldWidth {

	/**
	 * The {@link PropertyField} (and {@link PropertyPanel}) will be limited.
	 * See {@link PropertyPanelStyle#getMaxSmallWidth()) and
	 * {@link PropertyPanelStyle#getMinSmallWidth())
	 */
	SMALL,
	/**
	 * The {@link PropertyField} (and {@link PropertyPanel}) will get all
	 * available width within a {@link FormTab}
	 */
	FULL

}
