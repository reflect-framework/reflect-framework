package nth.introspect.ui.swing.view.form.field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import nth.introspect.generic.valuemodel.ReadWriteValueModel;
import nth.introspect.layer1userinterface.controller.Refreshable;
import nth.introspect.ui.swing.style.ColorUtil;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class CheckBox extends JCheckBox implements Refreshable{

	private static final long serialVersionUID = 3110259883941015708L;
	private ReadWriteValueModel propertyValueModel;

	public CheckBox(final PropertyValueModel propertyValueModel) {
		
		this.propertyValueModel = propertyValueModel;
		refresh();
		setBackground(ColorUtil.getLightColor());
		
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(isSelected());
				}
			}
		});
	}

	@Override
	public void refresh() {
		setEnabled(propertyValueModel.canSetValue());
		setSelected((Boolean) propertyValueModel.getValue());
	}
}
