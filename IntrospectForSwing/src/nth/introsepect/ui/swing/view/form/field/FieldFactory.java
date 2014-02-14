package nth.introsepect.ui.swing.view.form.field;

import java.awt.Component;

import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.userinterface.view.FormView;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class FieldFactory {

	public static Component create(FormView formView, PropertyValueModel propertyValueModel) {
		// TODO how do we make sure that all fields implement refreshable?
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		FieldModeType fieldMode = propertyInfo.getFieldMode();
		switch (fieldMode) {
		case TEXT:
			return new TextField(propertyValueModel);
		case PASSWORD:
			return new PasswordField(propertyValueModel);
		case TEXT_AREA:
			return new TextArea(propertyValueModel);
		case CHECK_BOX:
			return new CheckBox(propertyValueModel);
		case NUMBER:
		case CHAR:
			return new UniverselTextField(propertyValueModel);
		case COMBO_BOX:
			return new ComboBox(propertyValueModel);
		case ONE_TO_ONE_OR_MANY:
			return new OneToOneField(formView, propertyValueModel);
		case MANY_TO_ONE_OR_MANY:
			return new ManyToOneOrManyField(formView, propertyValueModel);
		default:
			throw new RuntimeException("Field mode " + fieldMode.name() + " not supported for property " + propertyInfo.getNamePath());
		}

	}

}
