package nth.introspect.provider.domain.format;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class NumericFormat extends DecimalFormat {

	private static final long serialVersionUID = 5081082083122386923L;

	public NumericFormat() {
		super();
	}
	
	public NumericFormat(String format) {
		super(format);
	}

	@Override
	public Number parse(String text, ParsePosition pos) {
		return super.parse(text, pos);
	}

	
	
	
}
