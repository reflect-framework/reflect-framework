package nth.introspect.ui.swing.view.form.field;

import java.text.Format;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.layer1userinterface.controller.Refreshable;
import nth.introspect.layer5provider.reflection.format.PropertyInfoFormatFactory;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class UniverselTextField extends JFormattedTextField implements
		Refreshable {
	// TODO refactor to UniversalTextField for char and numbers , using
	// StringConvertert and DocumentFactory

	private static final long serialVersionUID = 309612468612752404L;
	private PropertyValueModel valueModel;
	private Format formatter;

	public UniverselTextField(final PropertyValueModel valueModel) {
		super();
		this.valueModel = valueModel;
		// create a formatter (we do not pass it to the super class constructor,
		// because the superclass formatter is automatically replaces with a
		// InternationalFormatter
		formatter = valueModel.getPropertyInfo().getFormat();
		// restrict the user from entering invalid characters
		setDocument(RegExpDocumentFacory.create(valueModel.getValueType()));
		refresh();

		// TODO implement reflectionProvider.addPropertyChangeListener(new
		// addPropertyChangeListener(..

		// send changes back to value model
		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				updateValueModel();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				updateValueModel();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	@Override
	public void refresh() {
		// set the value and enabled state of the field
		setValue(valueModel.getValue());
		setEnabled(valueModel.canSetValue());
	}

	private void updateValueModel() {
		if (valueModel.canSetValue()) {
			String text = getText();
			try {
				Object value = formatter.parseObject(text);
				valueModel.setValue(value);
			} catch (ParseException e1) {
				// Should not happen. The program should prevent the user from
				// entering false values
			}
		}
	}

}
