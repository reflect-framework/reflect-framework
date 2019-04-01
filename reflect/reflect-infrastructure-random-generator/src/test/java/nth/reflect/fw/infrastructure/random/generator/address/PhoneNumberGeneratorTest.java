package nth.reflect.fw.infrastructure.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;

public class PhoneNumberGeneratorTest {

	private static final String NETHERLANDS = "Netherlands";

	@Test
	public void testForNoParameters() {
		Set<String> phoneNumberFormats = Resources.countryRepository().getAll().stream()
				.map(RandomCountry::getPhoneNumberFormat).collect(Collectors.toSet());
		List<String> phoneNumbers = Random.phoneNumber().generateList(10);
		assertThat(phoneNumbers)
				.allSatisfy(phoneNumber -> assertThat(isValid(phoneNumber, phoneNumberFormats)).isEqualTo(true));
	}

	@Test
	public void testForCountryWithPostalCode() {
		Optional<RandomCountry> netherlands = Resources.countryRepository().getAll().stream()
				.filter(country -> country.getName().equals(NETHERLANDS)).findFirst();
		assertTrue(netherlands.isPresent());
		List<String> phoneNumbers = Random.phoneNumber().forCountry(netherlands.get()).generateList(10);
		Set<String> phoneNumberFormats = new HashSet<>();
		phoneNumberFormats.add(netherlands.get().getPhoneNumberFormat());
		assertThat(phoneNumbers)
				.allSatisfy(phoneNumber -> assertThat(isValid(phoneNumber, phoneNumberFormats)).isEqualTo(true));
	}

	private boolean isValid(String phoneNumber, Set<String> phoneNumberFormats) {
		for (String format : phoneNumberFormats) {
			if (isValid(phoneNumber, format)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValid(String phoneNumber, String format) {
		if (phoneNumber.length() == 0) {
			return true;
		}
		if (phoneNumber.length() != format.length()) {
			return false;
		}
		Map<Character, CharacterSet> placeHoldersAndCharacterSets = CharacterSet.placeHoldersAndCharacterSets();
		Set<Character> placeHolders = placeHoldersAndCharacterSets.keySet();
		int index = 0;
		for (Character formatCharacter : format.toCharArray()) {
			if (placeHolders.contains(formatCharacter)) {
				CharacterSet characterSet = placeHoldersAndCharacterSets.get(formatCharacter);
				if (!characterSet.contains(phoneNumber.charAt(index))) {
					return false;
				}
				;
			} else {
				if (format.charAt(index) != phoneNumber.charAt(index)) {
					return false;
				}
				;
			}
			index++;
		}
		return true;
	}

}
