package nth.reflect.util.random.generator.address;

public class RandomAddress {
	private final String streetName;
	private final String houseNumber;
	private final String postalCode;
	private final String city;
	private final String region;
	private final String country;

	public RandomAddress(String streetName, String houseNumber, String postalCode, String city, String region,
			String country) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.region = region;
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

}
