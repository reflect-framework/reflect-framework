package nth.reflect.example.domain.contact;

import java.util.List;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.RandomCountry;

public class ContactGenerator extends RandomGenerator<Contact> {


	@Override
	public Contact generate() {
		boolean isMale = Random.bool().generate();
		int maleProbabilityInPercent = (isMale) ? 100 : 0;
		Contact contact = new Contact();

		String firstName = Random.firstName().forMaleProbability(maleProbabilityInPercent).generate();
		contact.setFirstName(firstName);

		boolean hasMiddleName = Random.bool().generate();
		if (hasMiddleName) {
			String middleName = Random.firstName().forMaleProbability(maleProbabilityInPercent).generate();
			if (!middleName.equals(firstName)) {
				contact.setMiddleName(middleName);
			}
		}

		String lastName = Random.lastName().generate();
		contact.setLastName(lastName);

		List<String> emailAddresses = Random.emailAddress().forName(contact.getFullName()).generateList(3);
		contact.geteMailAdresses().addAll(emailAddresses);

		RandomCountry country = Random.country().generate();
		
		List<Address> addresses = new AddressGenerator(country).generate();
		contact.setAddresses(addresses);
		
		List<String> phoneNumbers = Random.phoneNumber().forCountry(country).generateList(0,3);
		contact.setPhoneNumbers(phoneNumbers);
		
		List<Event> events = new EventGenerator().generate();
		contact.setEvents(events);
		
		return contact;
	}

}
