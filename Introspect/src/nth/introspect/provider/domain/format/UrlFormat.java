package nth.introspect.provider.domain.format;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;


public class UrlFormat extends Format {

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
			return  new URL(source);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

}
