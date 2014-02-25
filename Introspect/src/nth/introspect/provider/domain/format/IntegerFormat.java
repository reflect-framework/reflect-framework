package nth.introspect.provider.domain.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

@SuppressWarnings("serial")
public class IntegerFormat extends Format {

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		return toAppendTo.append((Integer) obj);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		if (source == null || source.length() < 1) {
			return null;
		}
		Integer i = Integer.valueOf(source);
		pos.setIndex(source.length());//success
		return i;

	}

}
