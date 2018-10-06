package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.HashSet;
import java.util.Optional;

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
	private final HashSet<Region> regions;
	
	public Country(String code, String name, String postalCodeFormat, String phoneCode,
			int phoneDigitsAfterCallingCode) {
		this.code = code;
		this.name = name;
		this.postalCodeFormat = postalCodeFormat;
		this.phoneCode = phoneCode;
		this.phoneDigitsAfterCallingCode = phoneDigitsAfterCallingCode;
		regions=new HashSet<Region>();
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
		StringBuilder format=new StringBuilder(phoneCode);
		format.append("-");
		int digitsAfterSeperator=0;
		for (int remainingDigits=phoneDigitsAfterCallingCode;remainingDigits>0;remainingDigits--) {
			if (digitsAfterSeperator>=3 && remainingDigits>=3) {
				format.append("-");
				digitsAfterSeperator=0;
			}
			format.append("#");
			digitsAfterSeperator++;
		}
		return format.toString();
	}

	public Optional<Region> findRegion(String regionName) {
		String regionNameToFind=regionName.trim().toLowerCase();
		return regions.stream().filter(region-> region.getName().toLowerCase().equals(regionNameToFind)).findFirst();
	}

	public  HashSet<Region> getRegions() {
		return regions;
	}
	
	
}
