package nth.introspect.ui.swing.view.form.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nth.introspect.Introspect;
import nth.introspect.controller.userinterface.Refreshable;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class DateTimeField extends JSpinner implements Refreshable {

	private static final long serialVersionUID = -7012735672468767713L;
	private PropertyValueModel propertyValueModel;
	private DateTimeMode dateTimeMode;

	public enum DateTimeMode {
		DATE, TIME, DATE_AND_TIME
	}

	// public Format dateFormat
	// public Format
	// spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));

	// FIXME show datepicker popup with button
	// FIXME set propertyValueModel when text is changed to a valid
	// date/calendar

	public DateTimeField(final PropertyValueModel propertyValueModel,
			DateTimeMode dateTimeMode) {
		this.propertyValueModel = propertyValueModel;
		this.dateTimeMode = dateTimeMode;
		setModel(new SpinnerDateModel());
		addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getValue());
				}
			}
		});
	}

	// private Action createDropDownButtonAction() {
	// return new AbstractAction() {
	//
	// private static final long serialVersionUID = 3204962085027822468L;
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// Component component = (Component) e.getSource();
	// JFrame frame = (JFrame) SwingUtilities.getRoot(component);
	// DatePickDialog dialog = new DatePickDialog(frame);// todo set
	// // dialog
	// // location
	//
	// }
	// };
	//
	// }

	// @Override
	// public JButton createDropDownButton() {
	// JButton button = super.createDropDownButton();
	// button.addActionListener(createDropDownButtonAction());
	// return button;
	// }

	@Override
	public void refresh() {

		// set text
		String formatPattern = getFormatPattern();
		setEditor(new JSpinner.DateEditor(this, formatPattern));

		Object value = propertyValueModel.getValue();
		if (value != null) {
			setValue(value);
		}

		// set enabled
		setEnabled(propertyValueModel.canSetValue());

	}

	private String getFormatPattern() {
		String formatPattern = propertyValueModel.getPropertyInfo()
				.getFormatPattern();
		if (formatPattern == null) {
			DateFormat format = null;
			switch (dateTimeMode) {
			case DATE:
				format = SimpleDateFormat
						.getDateInstance(SimpleDateFormat.MEDIUM);
				break;
			case TIME:
				format = SimpleDateFormat
						.getTimeInstance(SimpleDateFormat.MEDIUM);
				break;
			case DATE_AND_TIME:
				format = SimpleDateFormat.getDateTimeInstance(
						SimpleDateFormat.MEDIUM, SimpleDateFormat.MEDIUM);
				break;
			default:
				break;
			}
			try {
				Field patternField = SimpleDateFormat.class
						.getDeclaredField("pattern");
				patternField.setAccessible(true);
				formatPattern = (String) patternField.get(format);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return formatPattern;
	}

}
