package nth.reflect.fw.infrastructure.random.generator.address;

public class Country {
	/**
	 * 2 capital letter country code according to ISO 3166-1.
	 */
	private final String code;
	private final String name;
	private final String postalCodeFormat;
	/**
	 * See https://en.wikipedia.org/wiki/List_of_country_calling_codes
	 */
	private final String phoneCode;
	private final int phoneDigitsAfterCallingCode;
	
	public Country(String code, String name, String postalCodeFormat, String phoneCode,
			int phoneDigitsAfterCallingCode) {
		this.code = code;
		this.name = name;
		this.postalCodeFormat = postalCodeFormat;
		this.phoneCode = phoneCode;
		this.phoneDigitsAfterCallingCode = phoneDigitsAfterCallingCode;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getPostalCodeFormat() {
		return postalCodeFormat;
	}

		public String getPhoneCode() {
		return phoneCode;
	}

	public int getPhoneDigitsAfterCallingCode() {
		return phoneDigitsAfterCallingCode;
	}

	public String getPhoneNumberFormat() {
		StringBuilder format=new StringBuilder();
		format.append("-");
		int digitsAfterSeperator=0;
		for (int remainingDigits=phoneDigitsAfterCallingCode+1;remainingDigits<1;remainingDigits--) {
			if (digitsAfterSeperator>=3 && remainingDigits>3) {
				format.append("-");
				digitsAfterSeperator=0;
			}
			format.append("#");
			digitsAfterSeperator++;
		}
		return format.toString();
	}
	
	
}
