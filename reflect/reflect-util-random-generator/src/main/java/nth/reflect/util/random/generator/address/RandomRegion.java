package nth.reflect.util.random.generator.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Representing a region of a country, e.g. a state or a province.
 * @author nilsth
 *
 */
public class RandomRegion {
	
	private final String name;
	private final List<RandomCity> randomCities;
	
	public RandomRegion(String name) {
		this.name = name.trim();
		randomCities=new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<RandomCity> getCities() {
		return randomCities;
	}

	public Optional<RandomCity> findCity(String cityName) {
		String cityNameToFind = cityName.trim().toLowerCase();
		return randomCities.stream().filter(city-> city.getName().toLowerCase().equals(cityNameToFind)).findFirst();
	}
	
	
	
}
