package nth.introspect.layer5provider.reflection.info.valuemodel.impl;

import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.info.NameInfo;

public class AccessKeyValue extends TextValue {

	public AccessKeyValue(NameInfo introspectionInfo, LanguageProvider languageProvider, String name) {
		super(introspectionInfo, languageProvider, name);
	}
	
	@Override
	public Object getValue() {
		String text= ((String) super.getValue()).trim();
		if (text.length()>0) {
			return text.substring(0,1).toLowerCase();
		}
		return null;
	}
}
