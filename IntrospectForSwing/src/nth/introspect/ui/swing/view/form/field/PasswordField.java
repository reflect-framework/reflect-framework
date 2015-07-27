package nth.introspect.ui.swing.view.form.field;

import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.layer1userinterface.controller.Refreshable;
import nth.introspect.valuemodel.ReadWriteValueModel;

@SuppressWarnings("serial")
public class PasswordField extends JPasswordField implements Refreshable {

	private ReadWriteValueModel readWriteValueModel;

	public PasswordField(final ReadWriteValueModel readWriteValueModel) {

		this.readWriteValueModel = readWriteValueModel;
		refresh();

		// TODO implement domainInfoProvider.addPropertyChangeListener(new
		// addPropertyChangeListener(..

		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(String.valueOf(getPassword()));
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(String.valueOf(getPassword()));
				}
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
