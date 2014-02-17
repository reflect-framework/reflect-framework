package nth.introspect.ui.valuemodel;

import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.valuemodel.ReadWriteValueModel;

public class PropertyValueModel implements ReadWriteValueModel {

	private final PropertyInfo propertyInfo;
	private final BufferedDomainValueModel domainValueModel;
	private final boolean formIsReadOnly;

	public PropertyValueModel(BufferedDomainValueModel domainValueModel, PropertyInfo propertyInfo, boolean formIsReadOnly) {
		this.domainValueModel = domainValueModel;
		this.propertyInfo = propertyInfo;
		this.formIsReadOnly = formIsReadOnly;
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
		return !formIsReadOnly && propertyInfo.isEnabled(domainValueModel.getValue());
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
