package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.text.Format;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

@SuppressWarnings("serial")
public class NumericField extends JTextField implements  PropertyField {

	private final PropertyValueModel propertyValueModel;
	private boolean updatePropertyValue=false;
	
	public NumericField(PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		getDocument().addDocumentListener(createPropertyValueModelUpdater());
	}

	private DocumentListener createPropertyValueModelUpdater() {
		return new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				textToProperty();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				textToProperty();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		};
	}

	// private Document createFormatDocument(final Format format) {
	//
	// return new PlainDocument(){
	//
	// @Override
	// public void insertString(int offset, String str, AttributeSet a)
	// throws BadLocationException {
	//
	// String newText = getText(0,offset) + str + getText(offset,(getLength() -
	// offset));
	//
	// Object value = null;
	// try {
	// value = format.parseObject(newText);
	// } catch (ParseException e) {
	// }
	// String formatedText = format.format(value);
	//
	// if (newText.equals(formatedText)) {
	// super.insertString(offset, str, a);
	// }
	// }
	//
	//
	// };
	// }


	private void textToProperty() {
		// text has changed, so update the valueModel
		if (updatePropertyValue && propertyValueModel.canSetValue()) {
			Format format = propertyValueModel.getPropertyInfo().getFormat();
			String text = getText();
			try {
				Object value = format.parseObject(text);
				propertyValueModel.setValue(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				updatePropertyValue = false;

				Format format = propertyValueModel.getPropertyInfo()
						.getFormat();
				Object value = propertyValueModel.getValue();
				String text = format.format(value);
				setText(text);

				updatePropertyValue = true;
			}
		});
		
	}

}
