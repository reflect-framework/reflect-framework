package nth.reflect.fw.swing.tab.form.proppanel;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;

public class PropertyPanelFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory<PropertyPanel> {

	public PropertyPanelFactory(PropertyFieldProvider propertyFieldProvider) {
		super(propertyFieldProvider);
	}

	@Override
	protected PropertyPanel createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel,
			PropertyField propertyField) {
		return new PropertyPanel(formTab, propertyValueModel, propertyField);
	}

}
