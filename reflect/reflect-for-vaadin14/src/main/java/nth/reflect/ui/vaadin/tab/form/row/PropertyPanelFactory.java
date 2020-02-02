package nth.reflect.ui.vaadin.tab.form.row;

import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class PropertyPanelFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory<PropertyPanel> {

	public PropertyPanelFactory(GraphicalUserInterfaceApplication application) {
		super(application);
	}

	@Override
	protected PropertyPanel createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel,
			PropertyField propertyField) {
		return new PropertyPanel(formTab, propertyValueModel, propertyField);
	}

}
