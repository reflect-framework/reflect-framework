package nth.introspect.ui.form.field;

import nth.introspect.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.ui.view.FormMode;

public abstract class AbstractFormRowFactory<T> {

	
	//TODO why is this class not used
	public T createField(BufferedDomainValueModel domainValueModel, PropertyInfo propertyInfo, FormMode formMode ) {
		PropertyValueModel propertyValueModel=new PropertyValueModel(domainValueModel, propertyInfo, formMode);
		FieldModeType fieldMode = propertyInfo.getFieldMode();
		switch (fieldMode) {
		case TEXT:
			return createTextField(propertyValueModel);
		case PASSWORD:
			return createPasswordField(propertyValueModel);
		case TEXT_AREA:
			return createTextAreaField(propertyValueModel);
		case CHECK_BOX:
			return creatCheckBox(propertyValueModel);
		case NUMBER:
			return createNumberField(propertyValueModel);
		case CHAR:
			return createCharField(propertyValueModel);
		case COMBO_BOX:
			return createComboBox(propertyValueModel);
		case ONE_TO_ONE_OR_MANY:
			return createOneToOneOrManyField(propertyValueModel);
		case MANY_TO_ONE_OR_MANY:
			return createManyToOneOrManyField(propertyValueModel);
		default:
			throw new RuntimeException("Field mode " + fieldMode.name() + " not supported for property " + propertyInfo.getCanonicalName());
		}

	}
	
	public abstract T createLabel(PropertyInfo propertyInfo) ;
	
	public abstract T createValidationLabel(PropertyInfo propertyInfo) ;

	public abstract T createManyToOneOrManyField(PropertyValueModel propertyValueModel) ;

	public abstract T createOneToOneOrManyField(PropertyValueModel propertyValueModel);

	public abstract T createComboBox(PropertyValueModel propertyValueModel);

	public abstract T createCharField(PropertyValueModel propertyValueModel) ;

	public abstract T createNumberField(PropertyValueModel propertyValueModel) ;

	public abstract T creatCheckBox(PropertyValueModel propertyValueModel);

	public abstract T createTextAreaField(PropertyValueModel propertyValueModel);

	public abstract T createPasswordField(PropertyValueModel propertyValueModel) ;

	public abstract T createTextField(PropertyValueModel propertyValueModel);

}
