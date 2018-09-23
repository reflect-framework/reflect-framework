package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.table.RfxTable;
import nth.reflect.fw.javafx.control.view.form.proppanel.PropertyPanel;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.MaterialDesign;
import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.component.GridStyle;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public class ManyToOneOrManyField extends RfxTable implements PropertyField {


	private static final String READ_ONLY_STYLE = "read-only";
	private static final String EDITABLE_STYLE = "editable";

	public ManyToOneOrManyField(FormView formView, PropertyValueModel propertyValueModel) {
		super(formView, propertyValueModel);
		getStyleClass().add(RfxStyleSheet.createStyleClassName(ManyToOneOrManyField.class));
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

	@Override
	public void setEnabled(boolean enabled) {
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		// TODO Auto-generated method stub
		
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		appendStyleGroups( styleSheet,ManyToOneOrManyField.class, MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(), MaterialColorSetCssName.CONTENT.BACKGROUND());
	}

}
