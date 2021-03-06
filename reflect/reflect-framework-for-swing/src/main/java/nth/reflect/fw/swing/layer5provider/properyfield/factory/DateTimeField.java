package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;

@SuppressWarnings("serial")
public class DateTimeField extends JSpinner implements PropertyField {

	private final PropertyValueModel propertyValueModel;
	private final DateTimeFieldModeType dateTimeMode;

	// public Format dateFormat
	// public Format
	// spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));

	// FIXME show datepicker popup with button
	// FIXME set propertyValueModel when text is changed to a valid
	// date/calendar

	public DateTimeField(final PropertyValueModel propertyValueModel, DateTimeFieldModeType dateTimeMode) {
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

	private String getFormatPattern() {
		String formatPattern = propertyValueModel.getPropertyInfo().getFormatPattern();
		if (formatPattern == null) {
			DateFormat format = null;
			switch (dateTimeMode) {
			case DATE:
				format = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);
				break;
			case TIME:
				format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM);
				break;
			case DATE_TIME:
				format = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.MEDIUM, SimpleDateFormat.MEDIUM);
				break;
			default:
				break;
			}
			try {
				Field patternField = SimpleDateFormat.class.getDeclaredField("pattern");
				patternField.setAccessible(true);
				formatPattern = (String) patternField.get(format);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return formatPattern;
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		String formatPattern = getFormatPattern();
		setEditor(new JSpinner.DateEditor(this, formatPattern));

		Object value = propertyValueModel.getValue();
		if (value != null) {
			setValue(value);
		}
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
