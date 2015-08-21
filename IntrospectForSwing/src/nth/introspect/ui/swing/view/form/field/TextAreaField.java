package nth.introspect.ui.swing.view.form.field;

import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import nth.introspect.generic.valuemodel.ReadWriteValueModel;
import nth.introspect.layer1userinterface.controller.Refreshable;
import nth.introspect.ui.swing.properygrid.PropertyRow;
import nth.introspect.ui.swing.style.ColorUtil;

@SuppressWarnings("serial")
public class TextAreaField extends JTextArea implements Refreshable {

	private ReadWriteValueModel valueModel;

	public TextAreaField(final ReadWriteValueModel readWriteValueModel) {
		this.valueModel = readWriteValueModel;
		refresh();

		// same font and border as JTextField
		JTextField textFieldExample = new JTextField();
		setFont(textFieldExample.getFont());
		setBorder(textFieldExample.getBorder());

		//set preferred height (higher than most fields)
		setPreferredSize(new Dimension(Integer.MAX_VALUE,PropertyRow.HIGH_FIELD_HEIGHT));
		

		// TODO implement reflectionProvider.addPropertyChangeListener(new
		// addPropertyChangeListener(..

		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(getText());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (readWriteValueModel.canSetValue()) {
					readWriteValueModel.setValue(getText());
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	@Override
	public void refresh() {
		setText((String) valueModel.getValue());
		setEnabled(valueModel.canSetValue());
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		// Overriding background color behavior (Default background color when disabled is white)
		super.setEnabled(enabled);
		if (enabled) {
			setBackground(ColorUtil.getLightColor());
		} else {
			setBackground(ColorUtil.getMiddleColor());
		}
	}

}
