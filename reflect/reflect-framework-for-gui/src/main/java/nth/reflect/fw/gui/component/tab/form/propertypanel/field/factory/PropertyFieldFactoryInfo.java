package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyFieldFactoryInfo {

	private final PropertyValueModel propertyValueModel;
	private final FormTab formTab;

	public PropertyFieldFactoryInfo(FormTab formTab,
			PropertyValueModel propertyValueModel) {
		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;
	}

	public UserInterfaceContainer getUserInterfaceContainer() {
		return formTab.getUserInterfaceContainer();
	}

	public PropertyValueModel getPropertyValueModel() {
		return propertyValueModel;
	}

	public PropertyInfo getPropertyInfo() {
		return propertyValueModel.getPropertyInfo();
	}

	public FormTab getFormTab() {
		return formTab;
	}

}
