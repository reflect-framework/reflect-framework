package nth.introspect.layer5provider.reflection.behavior.format.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;


public class UrlFormat extends Format {

	private static final long serialVersionUID = 4785162820841286233L;

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj == null) {
			toAppendTo.append("");
		} else {
			URL url = (URL) obj;
			toAppendTo.append(url.toString());
		}
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		try {
			URL url = new URL(source);
			pos.setIndex(source.length());;
			return url;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

}
