package nth.reflect.util.random.generator.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nth.reflect.util.random.generator.resource.ResourceFile;
import nth.reflect.util.random.generator.resource.ResourceFileRepository;
import nth.reflect.util.random.generator.resource.Resources;
import nth.reflect.util.random.generator.text.CharacterSet;
import nth.reflect.util.random.generator.text.LetterCase;

/**
 * Creates a list of {@link RandomCountry} objects from a resource file. Source:
 * https://en.wikipedia.org/wiki/List_of_mobile_telephone_prefixes_by_country
 * 
 * @author nilsth
 *
 */
public class CountryRepository extends ResourceFileRepository<RandomCountry> {
	/**
	 * @return The {@link RandomCity} {@link ResourceFile} does not have regions and
	 *         cities for all countries. This method returns all countries with
	 *         {@link RandomRegion}s
	 */
	public List<RandomCountry> getCountriesThatHaveRegions() {
		List<RandomCountry> allCountries = getAll();
		List<RandomCountry> countriesWithKnwonRegions = allCountries.stream().filter(c -> !c.getRegions().isEmpty())
				.collect(Collectors.toList());
		return countriesWithKnwonRegions;
	}

	/**
	 * @return The {@link RandomCity} {@link ResourceFile} does not have regions and
	 *         cities for all countries. This method returns all countries that
	 *         have {@link RandomCity}s in the {@link RandomRegion}s
	 * 
	 */

	public List<RandomCountry> getCountriesThatHaveCities() {
		List<RandomCountry> countriesWithKnownRegions = getCountriesThatHaveRegions();
		List<RandomCountry> countriesWithKnownCities = countriesWithKnownRegions.stream()
				.filter(c -> c.getRegions().stream().filter(r -> !r.getCities().isEmpty()).count() > 0)
				.collect(Collectors.toList());
		return countriesWithKnownCities;
	}

	@Override
	public RandomCountry create(String line) {
		String[] values = line.split(";");
		return createCountry(values);
	}

	private RandomCountry createCountry(String[] values) {
		String countryCodeIso2 = getCountryCode(values[0]);
		String countryName = values[1].trim();
		String postalCodeFormat = getPostalCodeFormat(values[2]).trim();
		String phoneCode = values[3].trim();
		int phoneDigitsAfterCallingCode = getPhoneDigitsAfterCallingCode(values[4]);
		RandomCountry randomCountry = new RandomCountry(countryCodeIso2, countryName, postalCodeFormat, phoneCode,
				phoneDigitsAfterCallingCode);

		Set<RandomRegion> randomRegions = createRegions(randomCountry);
		randomCountry.getRegions().addAll(randomRegions);

		return randomCountry;
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

	private Set<RandomRegion> createRegions(RandomCountry country) {
		Set<RandomRegion> randomRegions = Resources.cityRepository().getRegionsOfCountry(country.getCode());
		return randomRegions;
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

	public List<RandomRegion> getAllKnowRegions() {
		List<RandomRegion> allKnownRegions = new ArrayList<>();
		List<RandomCountry> randomCountries= getAll();
		for (RandomCountry randomCountry : randomCountries) {
			allKnownRegions.addAll(randomCountry.getRegions());
		}
		return allKnownRegions;
	}
	
	public List<RandomCity> getAllKnowCities() {
		List<RandomRegion> allKnownRegions = getAllKnowRegions();
		List<RandomCity> allKnownCities = new ArrayList<>();
		for (RandomRegion knownRegions : allKnownRegions) {
			allKnownCities.addAll(knownRegions.getCities());
		}
		return allKnownCities;
	}

}
