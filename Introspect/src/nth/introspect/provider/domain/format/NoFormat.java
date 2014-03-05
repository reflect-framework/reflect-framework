package nth.introspect.provider.domain.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.introspect.util.exception.MethodNotSupportedException;

public class NoFormat extends Format {

	private static final long serialVersionUID = 18768767L;

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		if (obj!=null) {
			String value=obj.toString();
			toAppendTo.append(value);
		}
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		throw new MethodNotSupportedException();
	}

}
