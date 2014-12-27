package nth.introspect.ui.swing.view.form.field;

import java.awt.Component;
import java.net.URI;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.ui.swing.icon.IconFactory;

@SuppressWarnings("serial")
public class ComboBoxRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		
		
		if (value!=null && value.getClass().isEnum()) {
			LanguageProvider languageProvider = Introspect.getLanguageProvider();
			//set item text
			String key = languageProvider.getKey(value);
			String defaultValue = languageProvider.getDefaultValue(key);
			String text = languageProvider.getText(key, defaultValue);
			setText(text);
			//TODO? setToolTipText();
			
			//set icon
			try {
				PathProvider pathProvider = Introspect.getPathProvider();
				URI iconURI = pathProvider .getImagePath(key);
				Icon icon=IconFactory.create(iconURI);
				setIcon(icon);
			} catch (Exception e) {
				//failed
			}

		}
		return this;
	}


}
