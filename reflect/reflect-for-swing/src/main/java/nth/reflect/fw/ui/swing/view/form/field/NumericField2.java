package nth.reflect.fw.ui.swing.view.form.field;

import java.text.Format;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.layer1userinterface.controller.Refreshable;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class NumericField2 extends JTextField implements Refreshable {

	private static final long serialVersionUID = 2005556164722532623L;

	private PropertyValueModel propertyValueModel;

	private boolean updatePropertyValue;

	public NumericField2(final PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		updatePropertyValue = false;

		// Document document =
		// RegExpDocumentFacory.create(propertyValueModel.getPropertyInfo().getPropertyType().getType());
		// setDocument(document);

		// Document
		// document=createFormatDocument(propertyValueModel.getPropertyInfo().getFormat());
		// setDocument(document);

		getDocument().addDocumentListener(new DocumentListener() {

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
		});

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

	@Override
	public void refresh() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				updatePropertyValue = false;

				Format format = propertyValueModel.getPropertyInfo()
						.getFormat();
				Object value = propertyValueModel.getValue();
				String text = format.format(value);
				setText(text);
				setEnabled(propertyValueModel.canSetValue());

				updatePropertyValue = true;
			}
		});

	}

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

}
