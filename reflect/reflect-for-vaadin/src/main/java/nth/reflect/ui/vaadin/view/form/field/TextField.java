package nth.reflect.ui.vaadin.view.form.field;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

@SuppressWarnings("serial")
public class TextField extends FormFieldForVaadin {

	private final PropertyValueModel propertyValueModel;
	private final com.vaadin.flow.component.textfield.TextField textField;

	public TextField(PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		this.textField=new com.vaadin.flow.component.textfield.TextField();
		add(textField);
	}

	@Override
	public void updateLabel() {
		textField.setLabel(propertyValueModel.getPropertyInfo().getDisplayName());
	}

	@Override
	public void updateVisibility() {
		textField.setVisible(propertyValueModel.getPropertyInfo().isVisibleInForm(propertyValueModel.getDomainObject()));
		
	}

	@Override
	public void updateEnabled() {
		textField.setEnabled(propertyValueModel.getPropertyInfo().isEnabled(propertyValueModel.getDomainObject()));
		
	}

	@Override
	public void updateValue() {
		textField.setValue(propertyValueModel.getPropertyInfo().getFormatedValue(propertyValueModel.getDomainObject()));
	}

	
}
