package nth.introsepect.ui.swing.view.form.field;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.provider.domain.format.FormatFactory;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class UniverselTextField extends JFormattedTextField implements Refreshable {
	// TODO refactor to UniversalTextField for char and numbers , using StringConvertert and DocumentFactory

	private static final long serialVersionUID = 309612468612752404L;
	private PropertyValueModel valueModel;

	public UniverselTextField(final PropertyValueModel valueModel) {
		super(FormatFactory.create(valueModel.getPropertyInfo()));
		this.valueModel = valueModel;
		// restrict the user from entering invalid characters
		setDocument(DocumentFacory.create(valueModel.getValueType()));
		refresh();

		// TODO implement DomainProvider.addPropertyChangeListener(new addPropertyChangeListener(..

		// send changes back to value model
		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				Object value = getValue();
				valueModel.setValue(value);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				Object value = getValue();
				valueModel.setValue(value);
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

}
