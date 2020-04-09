package nth.reflect.util.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.address.RandomCountry;
import nth.reflect.util.random.generator.resource.Resources;
import nth.reflect.util.random.generator.text.CharacterSet;

public class PostalCodeGeneratorTest {
	private static final String NETHERLANDS = "Netherlands";

	@Test
	public void testForNoParameters() {
		Set<String> postalCodeFormats = Resources.countryRepository().getAll().stream()
				.map(RandomCountry::getPostalCodeFormat).collect(Collectors.toSet());
		List<String> postalCodes = Random.postalCode().generateList(10);
		assertThat(postalCodes).allSatisfy(postalCode -> assertThat(isValid(postalCode, postalCodeFormats)));
	}

	@Test
	public void testForCountryWithPostalCode() {
		Optional<RandomCountry> netherlands = Resources.countryRepository().getAll().stream()
				.filter(country -> country.getName().equals(NETHERLANDS)).findFirst();
		assertTrue(netherlands.isPresent());
		List<String> postalCodes = Random.postalCode().forCountry(netherlands.get()).generateList(10);
		Set<String> postalCodeFormats = new HashSet<>();
		postalCodeFormats.add(netherlands.get().getPostalCodeFormat());
		assertThat(postalCodes).allSatisfy(postalCode -> assertThat(isValid(postalCode, postalCodeFormats)));
	}

	private boolean isValid(String postalCode, Set<String> postalCodeFormats) {
		for (String format : postalCodeFormats) {
			if (isValid(postalCode, format)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValid(String postalCode, String format) {
		if (postalCode.length() == 0) {
			return true;
		}
		if (postalCode.length() != format.length()) {
			return false;
		}
		Map<Character, CharacterSet> placeHoldersAndCharacterSets = CharacterSet.placeHoldersAndCharacterSets();
		Set<Character> placeHolders = placeHoldersAndCharacterSets.keySet();
		int index = 0;
		for (Character formatCharacter : format.toCharArray()) {
			if (placeHolders.contains(formatCharacter)) {
				CharacterSet characterSet = placeHoldersAndCharacterSets.get(formatCharacter);
				if (!characterSet.contains(postalCode.charAt(index))) {
					return false;
				}
				;
			} else {
				if (format.charAt(index) != postalCode.charAt(index)) {
					return false;
				}
				;
			}
			index++;
		}
		return true;
	}

}
