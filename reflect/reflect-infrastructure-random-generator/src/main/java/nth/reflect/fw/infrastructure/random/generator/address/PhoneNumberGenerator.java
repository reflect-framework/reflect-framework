package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.ValueGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class PhoneNumberGenerator extends RandomGenerator<String> {

	public static final int DEFAULT_NR_OF_DIGITS_AFTER_CALLING_CODE = 9;
	private final RandomGenerator<String> phoneNumberFormatGenerator;

	public PhoneNumberGenerator() {
		 List<RandomCountry> randomCountries = Resources.countryRepository().getAll();
		List<String> phoneNumberFormats = randomCountries.stream().map(RandomCountry::getPhoneNumberFormat).collect(Collectors.toList());
		phoneNumberFormatGenerator = new FromListGenerator<String>(phoneNumberFormats);
	}

	public PhoneNumberGenerator(RandomGenerator<String> phoneNumberFormatGenerator) {
		this.phoneNumberFormatGenerator = phoneNumberFormatGenerator;
	}

	public PhoneNumberGenerator forCountry(RandomCountry country) {
		String phoneNumberFormatFormat = country.getPhoneNumberFormat();
		RandomGenerator<String> phoneNumberFormatGenerator = new ValueGenerator<String>(phoneNumberFormatFormat);
		return new PhoneNumberGenerator(phoneNumberFormatGenerator);
	}

	@Override
	public String generate() {
		String phoneNumberFormat = phoneNumberFormatGenerator.generate();
		String phoneNumber = Random.format().forFormat(phoneNumberFormat).generate();
		return phoneNumber;
	}

}
