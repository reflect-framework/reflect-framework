package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.util.Optional;

import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.generic.valuemodel.ReadWriteValueModel;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.layer1userinterface.item.Item;

@SuppressWarnings("serial")
public class PasswordField extends JPasswordField implements PropertyField {

	public PasswordField(final ReadWriteValueModel propertyValueModel) {
		getDocument().addDocumentListener(createPropertyValueModelUpdater(propertyValueModel));
	}

	private DocumentListener createPropertyValueModelUpdater(final ReadWriteValueModel readWriteValueModel) {
		return new DocumentListener() {

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
		};
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setText((String) propertyValue);
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}
}
