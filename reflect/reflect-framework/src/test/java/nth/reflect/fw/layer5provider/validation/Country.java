package nth.reflect.fw.layer5provider.validation;


public enum Country {
	NETHERLANDS("^[0-9]{4}[a-zA-Z]{2}$",
			"must contain 4 numbers followed by 2 letters"), FRANCE(
			"^[0-9]{5}$", "must contain 5 numbers");

	private final String regexpZipCode;
	private final String validationViolationMessage;

	private Country(String regexpZipCode, String validationViolationMessage) {
		this.regexpZipCode = regexpZipCode;
		this.validationViolationMessage = validationViolationMessage;
	}

	public String getValidationViolationMessage() {
		return validationViolationMessage;
	}

	public boolean isValidZipCode(String zipCode) {
		return zipCode.matches(regexpZipCode);
	}

	
	
}
