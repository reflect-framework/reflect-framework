package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.HashSet;
import java.util.Optional;

/**
 * Representing a region of a country, e.g. a state or a province.
 * @author nilsth
 *
 */
public class Region {
	
	private final String name;
	private final HashSet<City> cities;
	
	public Region(String name) {
		this.name = name.trim();
		cities=new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public HashSet<City> getCities() {
		return cities;
	}

	public Optional<City> findCity(String cityName) {
		String cityNameToFind = cityName.trim().toLowerCase();
		return cities.stream().filter(city-> city.getName().toLowerCase().equals(cityNameToFind)).findFirst();
	}
	
	
	
}
