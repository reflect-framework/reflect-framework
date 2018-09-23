package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public class OneToOneOrManyField extends RfxToDoField {

	public OneToOneOrManyField(FormView formView, PropertyValueModel propertyValueModel) {
		
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

}
