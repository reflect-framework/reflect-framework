package nth.reflect.util.random.generator.address;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.ValueGenerator;

public class AddressGenerator extends RandomGenerator<RandomAddress> {

	private final RandomGenerator<RandomCountry> countryGenerator;
	
	public AddressGenerator() {
		this(Random.country().forCountriesWithKnownCities());
	}
	
	
	public AddressGenerator(RandomGenerator<RandomCountry> countryGenerator) {
		this.countryGenerator=countryGenerator;
	}
	
	public AddressGenerator forCountry(RandomCountry randomCountry) {
		return new AddressGenerator(new ValueGenerator<RandomCountry>(randomCountry));
	}


	@Override
	public RandomAddress generate() {
		RandomCountry randomCountry=countryGenerator.generate();
		RandomRegion randomRegion=Random.region().forCountry(randomCountry).generate();
		RandomCity randomCity=Random.city().forRegion(randomRegion).generate();

		String streetName=Random.streetName().generate();
		String houseNumber=Random.integer().forRange(1, 9999) .generateString(1);
		String postalCode=Random.postalCode().forCountry(randomCountry).generate();
		String cityName=randomCity.getName();
		String regionName=randomRegion.getName();
		String countryName=randomCountry.getName();
		return new RandomAddress(streetName, houseNumber, postalCode, cityName, regionName, countryName);
	}

}
