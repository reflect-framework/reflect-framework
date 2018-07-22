package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class EnumFormat extends Format {

	private static final long serialVersionUID = 165765643765L;
	private final LanguageProvider languageProvider;
	
	
	public EnumFormat(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public StringBuffer format(Object enumValue, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (enumValue == null) {
			return toAppendTo.append("");
		} else {
			String key = languageProvider.getKey(enumValue);
			String defaultValue = StringUtil.eliphantCaseToNormal(enumValue.toString());
			String value = languageProvider.getText(key, defaultValue);
			return toAppendTo.append(value);
		}
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		throw new MethodNotSupportedException();
	}

}
