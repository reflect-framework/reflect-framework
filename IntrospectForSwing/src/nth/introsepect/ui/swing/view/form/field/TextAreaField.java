package nth.introsepect.ui.swing.view.form.field;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.valuemodel.ReadWriteValueModel;

@SuppressWarnings("serial")
public class TextAreaField extends JTextArea implements Refreshable {

	private ReadWriteValueModel valueModel;

	public TextAreaField(final ReadWriteValueModel readWriteValueModel) {

		this.valueModel = readWriteValueModel;
		refresh();

		// same font and border as JTextField
		JTextField textFieldExample = new JTextField();
		setFont(textFieldExample.getFont());
		setBorder(textFieldExample.getBorder());

		// TODO setHeigt (see ManyToOneOrMany)

		// TODO implement DomainProvider.addPropertyChangeListener(new
		// addPropertyChangeListener(..

		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(getText());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(getText());
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	@Override
	public void refresh() {
		setText((String) valueModel.getValue());
		setEnabled(valueModel.canSetValue());
	}

}
