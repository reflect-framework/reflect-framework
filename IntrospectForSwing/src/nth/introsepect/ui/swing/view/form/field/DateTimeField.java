package nth.introsepect.ui.swing.view.form.field;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class DateTimeField extends DropDownTextfield implements Refreshable {

	private static final long serialVersionUID = -7012735672468767713L;
	private PropertyValueModel propertyValueModel;

	public enum DateTimeMode {
		DATE, TIME, DATE_AND_TIME
	}

	// public Format dateFormat
	// public Format
	// spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));

	//FIXME show datepicker popup with button
	//FIXME set propertyValueModel when text is changed to a valid date/calendar
	
	public DateTimeField(PropertyValueModel propertyValueModel,
			DateTimeMode dateTimeMode) {
		this.propertyValueModel = propertyValueModel;
	}

	@Override
	public void refresh() {
		// set text
		DomainProvider domainProvider = Introspect.getDomainProvider();
		ClassInfo classInfo = domainProvider.getClassInfo(propertyValueModel
				.getValueType());
		Object propertyValue = propertyValueModel.getValue();
		String title = classInfo.getTitle(propertyValue);
		getTextField().setText(title);
		// TODO date/time format!!!
		// TODO description?
		getTextField().setEnabled(propertyValueModel.canSetValue());

	}

}
