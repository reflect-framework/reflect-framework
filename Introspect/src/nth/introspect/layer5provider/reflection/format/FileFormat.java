package nth.introspect.layer5provider.reflection.format;

import java.io.File;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class FileFormat extends Format {

	private static final long serialVersionUID = -8051805805780666828L;

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj == null) {
			toAppendTo.append("");
		} else {

			File file = (File) obj;
			toAppendTo.append(file.getAbsolutePath());
		}
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		File file = new File(source);
		pos.setIndex(source.length());
		return file;
	}

}
