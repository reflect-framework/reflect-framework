package nth.reflect.fw.ui.swing.view.form.proppanel.field;

import java.text.Format;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public class UniversalTextField extends JFormattedTextField implements
		 PropertyField {
	// TODO refactor to CharFieldFactory for char and numbers , using
	// StringConvertert and DocumentFactory

	private static final long serialVersionUID = 309612468612752404L;
	private PropertyValueModel valueModel;
	private Format formatter;

	public UniversalTextField(final PropertyValueModel valueModel) {
		super();
		this.valueModel = valueModel;
		// create a formatter (we do not pass it to the super class constructor,
		// because the superclass formatter is automatically replaces with a
		// InternationalFormatter
		formatter = valueModel.getPropertyInfo().getFormat();
		// restrict the user from entering invalid characters
		setDocument(RegExpDocumentFacory.create(valueModel.getValueType()));

		getDocument().addDocumentListener(createPropertyValueModelUpdater());
	}

	private DocumentListener createPropertyValueModelUpdater() {
		return new DocumentListener() {

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
		};
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

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setValue(propertyValue);
	}

}
