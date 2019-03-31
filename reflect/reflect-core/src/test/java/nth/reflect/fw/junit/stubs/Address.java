package nth.reflect.fw.junit.stubs;

import javax.validation.constraints.NotNull;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.validation.ValidationViolations;

public class Address {

	private Country country;
	private String street;
	private String zipCode;

	public Address(String street, String zipCode, Country country) {
		this.street = street;
		this.zipCode = zipCode;
		this.country = country;
	}

	public ValidationViolations addressExistsValidation() {
		ValidationViolations validationViolations = new ValidationViolations();
		if (!GoogleMapsClient.exists(this)) {
			validationViolations.add("address does not exist", this);
		}
		return validationViolations;
	}

	@NotNull
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public ValidationViolations zipCodeValidation() {
		ValidationViolations validationViolations = new ValidationViolations();
		if (country != null && !country.isValidZipCode(zipCode)) {
			validationViolations.add(country.getValidationViolationMessage(), zipCode);
		}
		// ETC
		return validationViolations;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance(", ").append(street).append(zipCode).append(country).toString();
	}

}
