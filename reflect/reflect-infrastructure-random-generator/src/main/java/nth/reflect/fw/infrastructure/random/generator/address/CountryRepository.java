package nth.reflect.fw.infrastructure.random.generator.address;

import nth.reflect.fw.infrastructure.random.generator.resource.Repository;
import nth.reflect.fw.infrastructure.random.generator.resource.ResourceFileRepository;

public class CountryRepository extends ResourceFileRepository<Country> implements Repository<Country> {

	@Override
	public Country create(String line) {
		String[] values = line.split(";");
		String code=values[0];
		String name=values[1];
		String postalCodeFormat=(values.length>=2)?"":values[2];
		String phoneCode="!";//TODO
		int phoneDigitsAfterCallingCode=10;//TODO
		Country country=new Country(code, name, postalCodeFormat, phoneCode, phoneDigitsAfterCallingCode);
		return country;
	}

}
