package nth.reflect.fw.junit.stubs;

public class GoogleMapsClient {

	public static boolean exists(Address address) {
		return notEmpty(address.getStreet()) && notEmpty(address.getZipCode())
				&& address.getCountry() != null
				&& !address.getStreet().toLowerCase().contains("bogus");
	}

	private static boolean notEmpty(String text) {
		return text != null && text.trim().length() > 0;
	}

}
