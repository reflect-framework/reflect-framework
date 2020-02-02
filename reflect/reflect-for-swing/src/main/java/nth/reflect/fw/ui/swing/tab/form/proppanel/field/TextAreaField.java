package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.awt.Dimension;
import java.util.Optional;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.reflect.fw.generic.valuemodel.ReadWriteValueModel;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.swing.style.ColorUtil;

@SuppressWarnings("serial")
public class TextAreaField extends JTextArea implements PropertyField {

	public TextAreaField(final ReadWriteValueModel propertyValueModel) {

		// same FONT and border as JTextField
		JTextField textFieldExample = new JTextField();
		setFont(textFieldExample.getFont());
		setBorder(textFieldExample.getBorder());

		// set preferred HEIGHT (higher than most fields)
		setPreferredSize(new Dimension(Integer.MAX_VALUE, PropertyFieldStyle.getMaxHeight()));

		getDocument().addDocumentListener(createPropertyValueModelUpdater(propertyValueModel));
	}

	private DocumentListener createPropertyValueModelUpdater(final ReadWriteValueModel propertyValueModel) {
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
	public void setEnabled(boolean enabled) {
		// Overriding background color behavior (Default background color when
		// disabled is white)
		super.setEnabled(enabled);
		if (enabled) {
			setBackground(ColorUtil.getLightColor());
		} else {
			setBackground(ColorUtil.getMiddleColor());
		}
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
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
