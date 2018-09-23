package nth.reflect.example.domain.contact;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.bval.constraints.NotEmpty;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Contact {
	private static final String UNKOWN = "unkown";
	private String firstName;
	private String middleName;
	private String lastName;
	private List<PhoneNumber> phoneNumbers;
	private List<EmailAddress> eMailAdresses;
	private List<Address> addresses;
	private List<Event> events;

	public Contact() {
		phoneNumbers = new ArrayList<>();
		eMailAdresses = new ArrayList<>();
		addresses = new ArrayList<>();
		events = new ArrayList<>();
	}

	@Order(sequenceNumber = 10)
	@NotEmpty
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Order(sequenceNumber = 20)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Order(sequenceNumber = 30)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Order(sequenceNumber = 40)
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Order(sequenceNumber = 50)
	public List<EmailAddress> geteMailAdresses() {
		return eMailAdresses;
	}

	public void seteMailAdresses(List<EmailAddress> eMailAdresses) {
		this.eMailAdresses = eMailAdresses;
	}

	@Order(sequenceNumber = 60)
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Order(sequenceNumber = 70)
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getAge() {
		LocalDate today = LocalDate.now();
		for (Event event : events) {
			if (event.isBirthday()) {
				LocalDate birthDay = event.getDate();
				if (birthDay == null) {
					return UNKOWN;
				}
				return Integer.toString(Period.between(birthDay, today).getYears());
			}
		}
		return UNKOWN;
	}

}
