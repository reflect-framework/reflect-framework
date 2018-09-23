package nth.reflect.fw.ui.swing.view.form.proppanel.field;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldFactory;

public class OneToOneOrManyFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormView formView, PropertyValueModel propertyValueModel) {
		return new OneToOneOrManyField(formView, propertyValueModel);
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode()==FieldModeType.ONE_TO_ONE_OR_MANY;
	}

}
