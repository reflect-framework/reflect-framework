package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

@SuppressWarnings("serial")
public class CharacterFormat extends Format {

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		return toAppendTo.append((Character)obj);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		if (source==null || source.length()<1) {
			return null;
		}
		pos.setIndex(1);
		return source.charAt(0);
	}

}
