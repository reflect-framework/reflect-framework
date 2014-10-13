package nth.introspect.provider.domain.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;

public class BooleanFormat extends Format {

	
	
	private static final String FALSE = "False";
	private static final String TRUE = "True";

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
	
		boolean bool=(Boolean) obj;
		String boolText = bool?TRUE:FALSE;
		return toAppendTo.append(boolText);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		if (source==null || source.trim().length()==0) {
			return null;
		} else {
			if (source.toLowerCase().trim().equals(TRUE.toLowerCase())) {
				return true;
			} else if (source.toLowerCase().trim().equals(FALSE.toLowerCase())) {
				return false;
			} else {
				return null;		
			}
		}
	}

}
