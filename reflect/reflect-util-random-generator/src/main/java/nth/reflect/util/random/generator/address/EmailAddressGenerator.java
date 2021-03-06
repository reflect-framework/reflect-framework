package nth.reflect.util.random.generator.address;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.ValueGenerator;
import nth.reflect.util.random.generator.name.CompanyNameGenerator;
import nth.reflect.util.random.generator.name.DomainNameGenerator;

/**
 * Generates random list of typical English names, using
 * 
 * @author nilsth
 *
 */
public class EmailAddressGenerator extends RandomGenerator<String> {

	private static final String REG_EXP_SPACES = "\\s+";
	private final CompanyNameGenerator companyNameGenerator;
	private final RandomGenerator<String> personNameGenerator;
	private final DomainNameGenerator domainNameGenerator;

	public EmailAddressGenerator() {
		this.personNameGenerator = Random.personName();
		companyNameGenerator = Random.companyName();
		domainNameGenerator = Random.domainName();
	}

	public EmailAddressGenerator(RandomGenerator<String> personNameGenerator) {
		this.personNameGenerator = personNameGenerator;
		companyNameGenerator = Random.companyName();
		domainNameGenerator = Random.domainName();
	}

	public EmailAddressGenerator forName(String name) {
		return new EmailAddressGenerator(new ValueGenerator<String>(name));
	}

	public EmailAddressGenerator forMaleProbability(int maleProbabilityInPercent) {
		return new EmailAddressGenerator(Random.personName().forMaleProbability(maleProbabilityInPercent));
	}

	@Override
	public String generate() {
		StringBuilder emailAddress = new StringBuilder();

		appendName(emailAddress);

		emailAddress.append("@");
		
		appendProvider(emailAddress);

		appendDomain(emailAddress);

		return emailAddress.toString();
	}

	private void appendName(StringBuilder emailAddress) {
		String[] names = personNameGenerator.generate().split(REG_EXP_SPACES);

		String firstName = names[0];
		String lastName = "";
		if (names.length > 1) {
			lastName = names[names.length-1];
		}

		Boolean firstLetterOnly = Random.bool().forProbability(33).generate();
		if (!lastName.isEmpty() && firstLetterOnly && firstName.length() > 1) {
			firstName = firstName.substring(0, 1);
		}

		Boolean firstNameLowerCase = Random.bool().forProbability(33).generate();
		if (firstNameLowerCase) {
			firstName = firstName.toLowerCase();
		}

		appendValidCharacters(emailAddress, firstName);

		if (!lastName.isEmpty()) {
			Boolean dotBetweenNames = Random.bool().forProbability(33).generate();

			if (dotBetweenNames) {
				emailAddress.append(".");
			}

			boolean firstNameIsUpperCase = Character.isUpperCase(firstName.charAt(0));
			Boolean lastNameLowerCase = !firstNameIsUpperCase && Random.bool().forProbability(33).generate();
			if (lastNameLowerCase) {
				lastName = lastName.toLowerCase();
			}
			appendValidCharacters(emailAddress, lastName);
		}
	}

	private void appendDomain(StringBuilder emailAddress) {
		String domainName = domainNameGenerator.generate();
		emailAddress.append(domainName);
	}

	private void appendProvider(StringBuilder emailAddress) {
		String companyName = companyNameGenerator.generate().trim();
		String[] companyNameWords = companyName.split(REG_EXP_SPACES);
		String providerName = companyNameWords[0];
		
		if (Random.bool().generate()) {
			providerName = providerName.toLowerCase();
		}
		appendValidCharacters(emailAddress, providerName);
	}

	private void appendValidCharacters(StringBuilder emailAddress, String toAppend) {
		for (Character ch : toAppend.toCharArray()) {
			if (isValidCharachter(ch)) {
				emailAddress.append(ch);
			}
		}
	}

	private boolean isValidCharachter(Character ch) {
		return Character.isLetter(ch) || Character.isDigit(ch);
	}

}
