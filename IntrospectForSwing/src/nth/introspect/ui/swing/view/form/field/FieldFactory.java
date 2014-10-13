package nth.introspect.ui.swing.view.form.field;

import java.awt.Component;

import nth.introspect.container.IntrospectOuterContainer;
import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.ui.swing.view.form.field.DateTimeField.DateTimeMode;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.ui.view.FormView;

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
			return new TextAreaField(propertyValueModel);
		case CHECK_BOX:
			return new CheckBox(propertyValueModel);
		case NUMBER:
			return new NumericField2(propertyValueModel);
		case CHAR:
			return new UniverselTextField(propertyValueModel);
		case TIME:
			return new DateTimeField(propertyValueModel, DateTimeMode.TIME);
		case DATE:
			return new DateTimeField(propertyValueModel, DateTimeMode.DATE);
		case DATE_TIME:
			return new DateTimeField(propertyValueModel, DateTimeMode.DATE_AND_TIME);
		case COMBO_BOX:
			return new ComboBox(propertyValueModel);
		case ONE_TO_ONE_OR_MANY:
			return new OneToOneOrManyField(formView, propertyValueModel);
		case MANY_TO_ONE_OR_MANY:
			return new ManyToOneOrManyField(formView, propertyValueModel);
		default:
			throw new RuntimeException("Field mode " + fieldMode.name() + " not supported for property " + propertyInfo.getNamePath());
		}

	}

}
