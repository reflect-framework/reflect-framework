package nth.introsepect.ui.swing.view.form.field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.valuemodel.ReadWriteValueModel;

@SuppressWarnings("serial")
public class ComboBox extends JComboBox implements Refreshable {

	private ReadWriteValueModel readWriteValueModel;

	public ComboBox(final ReadWriteValueModel readWriteValueModel) {
		this.readWriteValueModel = readWriteValueModel;

		Class<?> valueType = readWriteValueModel.getValueType();
		if (valueType.isEnum()) {
			setRenderer(new ComboBoxRenderer());

			// add menu items per enumeration value

			Vector<Object> listValues = new Vector<Object>();
			listValues.add(null);
			Object[] enumValues = valueType.getEnumConstants();
			for (Object enumValue : enumValues) {
				listValues.add(enumValue);
			}
			setModel(new DefaultComboBoxModel(listValues));
		}

		refresh();

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(getSelectedItem());
				}
			}
		});
	}

	@Override
	public void refresh() {
		setSelectedItem(readWriteValueModel.getValue());
		setEnabled(readWriteValueModel.canSetValue());
	}
}
