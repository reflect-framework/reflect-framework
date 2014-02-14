package nth.introsepect.ui.swing.view.form.field;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.valuemodel.ReadWriteValueModel;

@SuppressWarnings("serial")
public class TextArea extends JTextArea implements Refreshable {

	private ReadWriteValueModel readWriteValueModel;

	public TextArea(final ReadWriteValueModel readWriteValueModel) {

		this.readWriteValueModel = readWriteValueModel;
		refresh();

		// same font and border as JTextField
		JTextField textFieldExample = new JTextField();
		setFont(textFieldExample.getFont());
		setBorder(textFieldExample.getBorder());

		// TODO implement DomainProvider.addPropertyChangeListener(new addPropertyChangeListener(..

		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				readWriteValueModel.setValue(getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				readWriteValueModel.setValue(getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	@Override
	public void refresh() {
		setText((String) readWriteValueModel.getValue());
		setEnabled(readWriteValueModel.canSetValue());
	}
}
