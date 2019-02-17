package nth.reflect.fw.gui.component.tab.form.valuemodel;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.generic.valuemodel.ReadWriteValueModel;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyValueModel implements ReadWriteValueModel {

	private final PropertyInfo propertyInfo;
	private final BufferedDomainValueModel domainValueModel;
	private final FormMode formMode;
	private final List<PropertyValueChangeListener> listeners;

	public PropertyValueModel(BufferedDomainValueModel domainValueModel, PropertyInfo propertyInfo, FormMode formMode) {
		this.domainValueModel = domainValueModel;
		this.propertyInfo = propertyInfo;
		this.formMode = formMode;
		listeners = new ArrayList<>();
	}

	@Override
	public Object getValue() {
		return propertyInfo.getValue(domainValueModel.getValue());
	}

	@Override
	public void setValue(Object value) {
		if (canSetValue()) {
			propertyInfo.setValue(domainValueModel.getValue(), value);
			invokeListeners();
		} else {
			throw new RuntimeException("This method may not be called in read only mode!");
		}
	}

	private void invokeListeners() {
		for (PropertyValueChangeListener listener : listeners) {
			listener.onPropertyValueChange();
		}
	}

	@Override
	public boolean canSetValue() {
		return FormMode.EDIT == formMode && propertyInfo.isEnabled(domainValueModel.getValue());
	}

	public boolean isVisible() {
		return propertyInfo.isVisibleInForm(domainValueModel.getValue());
	}

	@Override
	public Class<?> getValueType() {
		return propertyInfo.getTypeInfo().getGenericType();
	}

	@Override
	public boolean canGetValue() {
		return true;
	}

	public PropertyInfo getPropertyInfo() {
		return propertyInfo;

	}

	public Object getDomainObject() {
		return domainValueModel.getValue();
	}

	public void addListener(PropertyValueChangeListener listener) {
		listeners.add(listener);
	}

}
