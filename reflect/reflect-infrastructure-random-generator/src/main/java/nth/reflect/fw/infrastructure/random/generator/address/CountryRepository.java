package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.Set;

import nth.reflect.fw.infrastructure.random.generator.resource.ResourceFileRepository;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;
/**
 * Creates a list of {@link Country} objects from a resource file.
 * Source: https://en.wikipedia.org/wiki/List_of_mobile_telephone_prefixes_by_country
 * @author nilsth
 *
 */
public class CountryRepository extends ResourceFileRepository<Country> {

	@Override
	public Country create(String line) {
		String[] values = line.split(";");
		return createCountry(values);
	}

	private Country createCountry(String[] values) {
		String countryCodeIso2 = values[0];
		String countryName = values[1];
		String postalCodeFormat = getPostalCodeFormat(values[2]);
		String phoneCode = values[3];
		int phoneDigitsAfterCallingCode = getPhoneDigitsAfterCallingCode(values[4]);
		Country country = new Country(countryCodeIso2, countryName, postalCodeFormat, phoneCode,
				phoneDigitsAfterCallingCode);

		Set<Region> regions = createRegions(country);
		country.getRegions().addAll(regions);

		return country;
	}

	private Set<Region> createRegions(Country country) {
		Set<Region> regions = Resources.cityRepository().getRegionsOfCountry(country.getCode());
		return regions;
	}

	private int getPhoneDigitsAfterCallingCode(String phoneDigitsAfterCallingCode) {
		if ("?".equals(phoneDigitsAfterCallingCode)) {
			return PhoneNumberGenerator.DEFAULT_NR_OF_DIGITS_AFTER_CALLING_CODE;
		} else {
			return Integer.parseInt(phoneDigitsAfterCallingCode);
		}
	}

	private String getPostalCodeFormat(String postalCodeFormat) {
		if ("?".equals(postalCodeFormat)) {
			postalCodeFormat = PostalCodeGenerator.DEFAULT_POSTAL_CODE_FORMAT;
		}
		return postalCodeFormat;
	}

}
