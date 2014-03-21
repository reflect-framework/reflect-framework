package nth.introsepect.ui.swing.view.form.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.valuemodel.ReadWriteValueModel;

@SuppressWarnings("serial")
public class ComboBox extends JComboBox implements Refreshable {

	private ReadWriteValueModel readWriteValueModel;

	public ComboBox(final PropertyValueModel propertyValueModel) {
		this.readWriteValueModel = propertyValueModel;

		Class<?> valueType = propertyValueModel.getValueType();
		if (valueType.isEnum()) {
			setRenderer(new ComboBoxRenderer());

			// add menu items per enumeration value

			Format format = propertyValueModel.getPropertyInfo().getFormat();
			
			Vector<Object> listValues = new Vector<Object>();
			listValues.add(null);
			Object[] enumValues = valueType.getEnumConstants();
			for (Object enumValue : enumValues) {
				listValues.add(enumValue);
			}
			setModel(new DefaultComboBoxModel(listValues));
			setRenderer(createRenderer(format));
		}

		refresh();

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getSelectedItem());
				}
			}
		});
	}

	private ListCellRenderer createRenderer(final Format format) {
		return new BasicComboBoxRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				String text = format.format(value);
				setText(text);
				
				return this; 
			}

		};
	}

	@Override
	public void refresh() {
		setSelectedItem(readWriteValueModel.getValue());
		setEnabled(readWriteValueModel.canSetValue());
	}
}
