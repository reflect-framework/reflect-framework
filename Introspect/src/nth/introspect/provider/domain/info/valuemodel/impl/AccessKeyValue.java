package nth.introspect.provider.domain.info.valuemodel.impl;

import nth.introspect.provider.domain.info.IntrospectionInfo;
import nth.introspect.provider.language.LanguageProvider;

public class AccessKeyValue extends TextValue {

	public AccessKeyValue(IntrospectionInfo introspectionInfo, LanguageProvider languageProvider, String name) {
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
