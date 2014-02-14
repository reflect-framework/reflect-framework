package nth.introsepect.ui.swing.view.form.field;

import java.awt.Component;
import java.net.URI;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;

import nth.introsepect.ui.swing.icon.IconFactory;
import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;

@SuppressWarnings("serial")
public class ComboBoxRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		
		
		if (value!=null && value.getClass().isEnum()) {
			LanguageProvider languagePort = Introspect.getLanguageProvider();
			//set item text
			String key = languagePort.getKey(value);
			String defaultValue = languagePort.getDefaultValue(key);
			String text = languagePort.getText(key, defaultValue);
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
