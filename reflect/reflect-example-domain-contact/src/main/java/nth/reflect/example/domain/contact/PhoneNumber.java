package nth.reflect.example.domain.contact;

import javax.validation.constraints.Pattern;

import org.apache.bval.constraints.NotEmpty;

public class PhoneNumber {
	private String phoneNumber;

	@NotEmpty
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
