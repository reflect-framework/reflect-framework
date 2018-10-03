package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.List;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;

public class PostalCodeGenerator extends StringGenerator {

	private final RandomGenerator<String> postalCodeFormatGenerator;

	public PostalCodeGenerator() {
		List<String> postalCodeFormats = Repositories.countryAndPostalCodeRepository().getPostalCodeFormats();
		postalCodeFormatGenerator = Random.fromStringListGenerator().forValues(postalCodeFormats);
	}

	public PostalCodeGenerator(RandomGenerator<String> postalCodeFormatGenerator) {
		this.postalCodeFormatGenerator = postalCodeFormatGenerator;
	}

	public PostalCodeGenerator forCountry(String country) {
		List<String> postalCodeFormats = Repositories.countryAndPostalCodeRepository().getPostalCodeFormats(country);
		RandomGenerator<String> postalCodeFormatGenerator = Random.fromStringListGenerator()
				.forValues(postalCodeFormats);
		return new PostalCodeGenerator(postalCodeFormatGenerator);
	}

	@Override
	public String generate() {
		String postCodeFormat = postalCodeFormatGenerator.generate();
		String postalCode = Random.formatGenerator().forFormat(postCodeFormat).generate();
		return postalCode;
	}

}
