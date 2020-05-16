package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import java.util.Optional;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class UniversalTextField extends JFormattedTextField implements PropertyField {
	// TODO refactor to CharFieldFactory for char and numbers , using
	// StringConvertert and DocumentFactory

	private static final long serialVersionUID = 309612468612752404L;
	private final PropertyValueModel valueModel;
	private final Optional<StringConverter> stringConverter;

	public UniversalTextField(final PropertyValueModel valueModel) {
		super();
		this.valueModel = valueModel;
		// create a formatter (we do not pass it to the super class constructor,
		// because the superclass formatter is automatically replaces with a
		// InternationalFormatter
		stringConverter = valueModel.getPropertyInfo().getStringConverter();
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
		if (valueModel.canSetValue() && stringConverter.isPresent()) {
			String text = getText();
			Object value = stringConverter.get().fromString(text);
			valueModel.setValue(value);
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

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}
}
