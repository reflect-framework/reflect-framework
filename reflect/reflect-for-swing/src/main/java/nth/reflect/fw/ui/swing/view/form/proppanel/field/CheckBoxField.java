package nth.reflect.fw.ui.swing.view.form.proppanel.field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import nth.reflect.fw.ui.swing.style.ColorUtil;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

@SuppressWarnings("serial")
public class CheckBoxField extends JCheckBox implements  PropertyField{


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
}
