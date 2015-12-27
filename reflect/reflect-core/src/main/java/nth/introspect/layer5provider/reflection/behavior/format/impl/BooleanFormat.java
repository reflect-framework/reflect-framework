package nth.introspect.layer5provider.reflection.behavior.format.impl;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class BooleanFormat extends Format {

	private static final long serialVersionUID = 4661147736928871346L;
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
				pos.setIndex(source.length());
				return true;
			} else if (source.toLowerCase().trim().equals(FALSE.toLowerCase())) {
				pos.setIndex(source.length());
				return false;
			} else {
				return null;		
			}
		}
	}

}
