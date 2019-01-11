package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JCheckBox;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.swing.style.ColorUtil;

@SuppressWarnings("serial")
public class CheckBoxField extends JCheckBox implements PropertyField {

	public CheckBoxField(final PropertyValueModel propertyValueModel) {
		setBackground(ColorUtil.getLightColor());
		addActionListener(createPropertyValueModelUpdater(propertyValueModel));
	}

	private ActionListener createPropertyValueModelUpdater(final PropertyValueModel propertyValueModel) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(isSelected());
				}
			}
		};
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setSelected((Boolean) propertyValue);
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}
}
