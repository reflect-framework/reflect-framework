package nth.reflect.ui.vaadin.view.form.field;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ui.view.FormFieldFactory;
import nth.reflect.fw.ui.view.FormFieldSubFactory;

public class FormFieldFactoryForVaadin extends FormFieldFactory<FormFieldForVaadin> {


	private final List<FormFieldSubFactory<FormFieldForVaadin>> formFieldSubFactories;

	public FormFieldFactoryForVaadin() {
		formFieldSubFactories=new ArrayList<>();
		formFieldSubFactories.add(new TextFieldSubFactory());
	}
	
	@Override
	public List<FormFieldSubFactory<FormFieldForVaadin>> getFormFieldSubFactories() {
		return formFieldSubFactories;
	}

}
