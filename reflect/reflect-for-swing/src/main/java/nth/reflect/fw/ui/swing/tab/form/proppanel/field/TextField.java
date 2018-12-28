package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

@SuppressWarnings("serial")
public class TextField extends JTextField implements PropertyField {

	public TextField (PropertyValueModel propertyValueModel) {
		getDocument().addDocumentListener(createUpdater(propertyValueModel));
	}

	private DocumentListener createUpdater(PropertyValueModel propertyValueModel) {
		return new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getText());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getText());
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		};
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setText((String) propertyValue);
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}


}
