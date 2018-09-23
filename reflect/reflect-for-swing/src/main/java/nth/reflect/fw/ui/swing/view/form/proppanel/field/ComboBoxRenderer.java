package nth.reflect.fw.ui.swing.view.form.proppanel.field;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

@SuppressWarnings("serial")
public class ComboBoxRenderer extends DefaultListCellRenderer {

	private final LanguageProvider languageProvider;
	
	public ComboBoxRenderer(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public Component getListCellRendererComponent( @SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		
		
		if (value!=null && value.getClass().isEnum()) {
			//set item text
			String key = languageProvider.getKey(value);
			String defaultValue = languageProvider.getDefaultValueFromKey(key);
			String text = languageProvider.getText(key, defaultValue);
			setText(text);
			//TODO? setToolTipText();
			//TODO set MaterialAppBarIcon

		}
		return this;
	}


}
