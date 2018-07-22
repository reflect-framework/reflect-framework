package nth.reflect.fw.ui.valuemodel;

import nth.reflect.fw.generic.valuemodel.ReadWriteValueModel;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.view.FormMode;

public class PropertyValueModel implements ReadWriteValueModel {

	private final PropertyInfo propertyInfo;
	private final BufferedDomainValueModel domainValueModel;
	private FormMode formMode;

	public PropertyValueModel(BufferedDomainValueModel domainValueModel, PropertyInfo propertyInfo, FormMode formMode) {
		this.domainValueModel = domainValueModel;
		this.propertyInfo = propertyInfo;
		this.formMode = formMode;
	}

	@Override
	public Object getValue() {
		return propertyInfo.getValue(domainValueModel.getValue());
	}

	@Override
	public void setValue(Object value) {
		if (canSetValue()) {
			propertyInfo.setValue(domainValueModel.getValue(), value);
		} else {
			throw new RuntimeException("This method may not be called in read only mode!");// Field should be disabled automatically
		}
	}

	@Override
	public boolean canSetValue() {
		return FormMode.EDIT_MODE==formMode && propertyInfo.isEnabled(domainValueModel.getValue());
	}

	public boolean isVisible() {
		return propertyInfo.isVisibleInForm(domainValueModel.getValue());
	}

	@Override
	public Class<?> getValueType() {
		return propertyInfo.getPropertyType().getTypeOrGenericCollectionType();
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


}
