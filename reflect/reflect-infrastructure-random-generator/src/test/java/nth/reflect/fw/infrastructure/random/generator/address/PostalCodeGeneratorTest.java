package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;

public class PostalCodeGeneratorTest {

	@Test
	public void testForNoParameters() {
		Set<String> allPostalCodeFormats = new HashSet<>(Repositories.countryAndPostalCodeRepository().getPostalCodeFormats());
		List<String> postalCodes = Random.postalCodeGenerator().generateList(10);
		assertPostalCode(postalCodes, allPostalCodeFormats);
	}

	

	@Test
	public void testForCountryWithPostalCode() {
		String country="Netherlands";
		List<String> postalCodes = Random.postalCodeGenerator().forCountry(country) .generateList(10);
		Set<String> allPostalCodeFormats = new HashSet<>(Repositories.countryAndPostalCodeRepository().getPostalCodeFormats(country));
		assertPostalCode(postalCodes, allPostalCodeFormats);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testForCountryWithoutPostalCode() {
		String country="Angola";
		List<String> postalCodes = Random.postalCodeGenerator().forCountry(country) .generateList(10);
		assertThat(postalCodes, hasItems(isEmptyString()));
	}

	
	private void assertPostalCode(List<String> postalCodes, Set<String> allPostalCodeFormats) {
		for (String postalCode : postalCodes) {
			assertTrue("Invalid postal code: "+postalCode, isValid(postalCode, allPostalCodeFormats));
		}
	}

	private boolean isValid(String postalCode, Set<String> allPostalCodeFormats) {
		for (String format : allPostalCodeFormats) {
			if (isValid(postalCode, format)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValid(String postalCode, String format) {
		if (postalCode.length()!=format.length()) {
			return false;
		}
		Map<Character, CharacterSet> placeHoldersAndCharacterSets = CharacterSet.placeHoldersAndCharacterSets();
		Set<Character> placeHolders = placeHoldersAndCharacterSets.keySet();
		int index=0;
		for (Character formatCharacter : format.toCharArray()) {
			if (placeHolders.contains(formatCharacter)) {
				CharacterSet characterSet=placeHoldersAndCharacterSets.get(formatCharacter);
				if (!  characterSet.contains(postalCode.charAt(index))) {
					return false;
				};
			} else {
				if (format.charAt(index)!= postalCode.charAt(index)) {
					return false;
				};
			}
			index++;
		}
		return true;
	}

}
