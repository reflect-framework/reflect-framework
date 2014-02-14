package nth.introsepect.ui.swing.view.form.field;

import javax.swing.JCheckBox;

import nth.introsepect.ui.swing.style.ColorUtil;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.valuemodel.ReadWriteValueModel;

public class CheckBox extends JCheckBox implements Refreshable{

	private static final long serialVersionUID = 3110259883941015708L;
	private ReadWriteValueModel readWriteValueModel;

	public CheckBox(ReadWriteValueModel readWriteValueModel) {
		
		this.readWriteValueModel = readWriteValueModel;
		refresh();
		setBackground(ColorUtil.getLightColor());
	}

	@Override
	public void refresh() {
		setEnabled(readWriteValueModel.canSetValue());
		setSelected((Boolean) readWriteValueModel.getValue());
		}
}
