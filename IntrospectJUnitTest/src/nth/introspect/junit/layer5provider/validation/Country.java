package nth.introspect.junit.layer5provider.validation;

import java.util.Locale;

public class Country {
	private final String name;
	private final String code;

	public Country(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	
}
