package nth.introsepect.ui.swing.view.form.field;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class DateTimeField extends DropDownTextfield<JSpinner> implements
		Refreshable {

	private static final long serialVersionUID = -7012735672468767713L;
	private PropertyValueModel propertyValueModel;

	public enum DateTimeMode {
		DATE, TIME, DATE_AND_TIME
	}

	// public Format dateFormat
	// public Format
	// spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));

	// FIXME show datepicker popup with button
	// FIXME set propertyValueModel when text is changed to a valid
	// date/calendar

	public DateTimeField(PropertyValueModel propertyValueModel,
			DateTimeMode dateTimeMode) {
		this.propertyValueModel = propertyValueModel;
	}

	private Action createDropDownButtonAction() {
		return new AbstractAction() {

			private static final long serialVersionUID = 3204962085027822468L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				JFrame frame = (JFrame) SwingUtilities.getRoot(component);
				DatePickDialog dialog = new DatePickDialog(frame);// todo set
																	// dialog
																	// location

			}
		};

	}

	@Override
	public JButton createDropDownButton() {
		JButton button = super.createDropDownButton();
		button.addActionListener(createDropDownButtonAction());
		return button;
	}

	@Override
	public JSpinner createTextField() {
		JSpinner spinner = new JSpinner();
		spinner.setBorder(BorderFactory.createEmptyBorder( 0,2,0,1));
		spinner.setModel(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));
		// TODO set editor and format according to propertyinfo
		return spinner;
	}

	@Override
	public void refresh() {
		// set text
		DomainProvider domainProvider = Introspect.getDomainProvider();
		ClassInfo classInfo = domainProvider.getClassInfo(propertyValueModel
				.getValueType());
		Object propertyValue = propertyValueModel.getValue();
		String title = classInfo.getTitle(propertyValue);
		// TODO getTextField().setText(title);
		// TODO date/time format!!!
		// TODO description?
		getTextField().setEnabled(propertyValueModel.canSetValue());

	}

}
