package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nth.reflect.fw.infrastructure.random.generator.resource.ResourceFile;
import nth.reflect.fw.infrastructure.random.generator.resource.ResourceFileRepository;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;
import nth.reflect.fw.infrastructure.random.generator.text.LetterCase;

/**
 * Creates a list of {@link Country} objects from a resource file. Source:
 * https://en.wikipedia.org/wiki/List_of_mobile_telephone_prefixes_by_country
 * 
 * @author nilsth
 *
 */
public class CountryRepository extends ResourceFileRepository<Country> {
	/**
	 * @return The {@link City} {@link ResourceFile} does not have regions and
	 *         cities for all countries. This method returns all countries with
	 *         {@link Region}s
	 */
	public List<Country> getWithAllKnownRegions() {
		List<Country> allCountries = getAll();
		List<Country> countriesWithKnwonRegions = allCountries.stream().filter(c -> !c.getRegions().isEmpty())
				.collect(Collectors.toList());
		return countriesWithKnwonRegions;
	}

	/**
	 * @return The {@link City} {@link ResourceFile} does not have regions and
	 *         cities for all countries. This method returns all countries that
	 *         have {@link City}s in the {@link Region}s
	 * 
	 */

	public List<Country> getWithAllKnownCities() {
		List<Country> countriesWithKnownRegions = getWithAllKnownRegions();
		List<Country> countriesWithKnownCities = countriesWithKnownRegions.stream()
				.filter(c -> c.getRegions().stream().filter(r -> !r.getCities().isEmpty()).count() > 0)
				.collect(Collectors.toList());
		return countriesWithKnownCities;
	}

	@Override
	public Country create(String line) {
		String[] values = line.split(";");
		return createCountry(values);
	}

	private Country createCountry(String[] values) {
		String countryCodeIso2 = getCountryCode(values[0]);
		String countryName = values[1].trim();
		String postalCodeFormat = getPostalCodeFormat(values[2]).trim();
		String phoneCode = values[3].trim();
		int phoneDigitsAfterCallingCode = getPhoneDigitsAfterCallingCode(values[4]);
		Country country = new Country(countryCodeIso2, countryName, postalCodeFormat, phoneCode,
				phoneDigitsAfterCallingCode);

		Set<Region> regions = createRegions(country);
		country.getRegions().addAll(regions);

		return country;
	}

	private String getCountryCode(String value) {
		CharacterSet upperCaseLetters=CharacterSet.letters(LetterCase.UPPER);
		StringBuilder countryCode= new StringBuilder();
		for (Character character : value.trim().toUpperCase().toCharArray()) {
			if (upperCaseLetters.contains(character)) {
				countryCode.append(character);
				if (countryCode.length()>=2) {
					return countryCode.toString();
				}
			}
		}
		return countryCode.toString();
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
