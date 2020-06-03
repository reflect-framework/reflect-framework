package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;

public abstract class PropertyPanelFactory<PROPERTY_PANEL> {

	private final PropertyFieldProvider propertyFieldProvider;

	public PropertyPanelFactory(PropertyFieldProvider propertyFieldProvider) {
		this.propertyFieldProvider = propertyFieldProvider;
	}

	/**
	 * Abstract method to create a property panel. This method uses the
	 * {@link PropertyFieldProvider} to create fields.
	 */
	public PROPERTY_PANEL createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyField propertyField = propertyFieldProvider.create(formTab, propertyValueModel);
		PROPERTY_PANEL propertyPanel = createPropertyPanel(formTab, propertyValueModel, propertyField);
		return propertyPanel;
	}

	/**
	 * Hook method
	 */
	protected abstract PROPERTY_PANEL createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel,
			PropertyField propertyField);
}
