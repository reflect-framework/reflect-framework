package nth.introspect.ui.swing.view.form.field;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.valuemodel.ReadWriteValueModel;

@SuppressWarnings("serial")
public class TextField extends JTextField implements Refreshable {

	private ReadWriteValueModel readWriteValueModel;

	public TextField(final ReadWriteValueModel readWriteValueModel) {

		this.readWriteValueModel = readWriteValueModel;
		refresh();

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
		// TODO implement DomainProvider.addPropertyChangeListener(new
		// addPropertyChangeListener(..

		setText((String) readWriteValueModel.getValue());
		setEnabled(readWriteValueModel.canSetValue());
	}

}
