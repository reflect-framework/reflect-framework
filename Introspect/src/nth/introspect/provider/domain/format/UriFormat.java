package nth.introspect.provider.domain.format;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;


public class UriFormat extends Format {

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj == null) {
			toAppendTo.append("");
		} else {
			URI uri = (URI) obj;
			toAppendTo.append(uri.toString());
		}
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		try {
			return  new URI(source);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
