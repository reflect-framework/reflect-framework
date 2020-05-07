package nth.reflect.example.domain.contact;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.RandomAddress;
import nth.reflect.fw.infrastructure.random.generator.address.RandomCountry;

public class AddressGenerator extends RandomGenerator<List<Address>> {

	private final nth.reflect.fw.infrastructure.random.generator.address.AddressGenerator addressGenerator;
	
	public AddressGenerator(RandomCountry country) {
		addressGenerator=Random.address().forCountry(country);
	}
	
	@Override
	public List<Address> generate() {
		List<Address> addresses = new ArrayList<>();
		if (Random.bool().generate()) {
			addresses.add( createAddress("Home"));
		}
		if (Random.bool().generate()) {
			addresses.add( createAddress("Work"));
		}
		if (Random.bool().forProbability(10) .generate()) {
			addresses.add( createAddress("Hoiliday Home"));
		}
		if (Random.bool().forProbability(10) .generate()) {
			addresses.add( createAddress("Sport club"));
		}
		return addresses;
	}

	private Address createAddress(String addressTypeName) {
		RandomAddress randomAddress = addressGenerator.generate();
		Address address=new Address();
		address.setStreetName(randomAddress.getStreetName());
		address.setHouseNumber(randomAddress.getHouseNumber());
		address.setPostalCode(randomAddress.getPostalCode());
		address.setCity(randomAddress.getCity());
		address.setRegion(randomAddress.getRegion() );
		address.setCountry(randomAddress.getCountry());
		address.setAddressType(createAddressType(addressTypeName));
		return address;
	}

	private AddressType createAddressType(String addressTypeName) {
		AddressType addressType=new AddressType();
		addressType.setAddressType(addressTypeName);
		return addressType;
	}

}
