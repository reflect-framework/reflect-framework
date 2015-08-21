package nth.introspect.ui.swing.view.form.field;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;

@SuppressWarnings("serial")
public class ComboBoxRenderer extends DefaultListCellRenderer {

	private final LanguageProvider languageProvider;
	private final PathProvider pathProvider;
	
	public ComboBoxRenderer(PathProvider pathProvider, LanguageProvider languageProvider) {
		this.pathProvider = pathProvider;
		this.languageProvider = languageProvider;
	}

	@Override
	public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		
		
		if (value!=null && value.getClass().isEnum()) {
			//set item text
			String key = languageProvider.getKey(value);
			String defaultValue = languageProvider.getDefaultValue(key);
			String text = languageProvider.getText(key, defaultValue);
			setText(text);
			//TODO? setToolTipText();
			//TODO set icon

		}
		return this;
	}


}
