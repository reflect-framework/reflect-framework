package nth.reflect.util.random.generator.address;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nth.reflect.util.random.generator.resource.ResourceFileRepository;

/**
 * The {@link CityRepository} is only used internally by the
 * {@link CountryRepository}
 * 
 * Source: 
 * 
 * @author nilsth
 *
 */
public class CityRepository extends ResourceFileRepository<List<String>> {

	private static final int CITY_NAME = 0;
	// private static final int CITY_NAME_ASCII = 1;
	private static final int CITY_LATITUDE = 2;
	private static final int CITY_LONGITUDE = 3;
	// private static final int COUNTRY = 4;
	private static final int COUNTRY_CODE_ISO2 = 5;
	// private static final int COUNTRY_CODE_ISO3 = 6;
	private static final int COUNTRY_REGION = 7;
	private static final int POPULATION = 10;

	@Override
	public List<String> create(String line) {
		String[] values = line.split(";");
		return Arrays.asList(values);
	}

	public Set<RandomRegion> getRegionsOfCountry(String countryCodeIso2) {
		List<List<String>> lines = getAll();
		List<List<String>> linesForCountry = lines.stream()
				.filter(line -> line.get(COUNTRY_CODE_ISO2).equals(countryCodeIso2)).collect(Collectors.toList());

		Set<String> regionsNames = linesForCountry.stream().map(line -> line.get(COUNTRY_REGION))
				.collect(Collectors.toSet());
		Set<RandomRegion> randomRegions = new HashSet<>();
		for (String regionName : regionsNames) {
			RandomRegion randomRegion = new RandomRegion(regionName);
			List<List<String>> cityLines = linesForCountry.stream()
					.filter(line -> line.get(COUNTRY_REGION).equals(regionName)).collect(Collectors.toList());
			Set<RandomCity> randomCities = createCities(cityLines);
			randomRegion.getCities().addAll(randomCities);
			randomRegions.add(randomRegion);
		}
		return randomRegions;
	}

	private Set<RandomCity> createCities(List<List<String>> cityLines) {
		Set<RandomCity> randomCities = new HashSet<>();
		for (List<String> cityLine : cityLines) {
			String cityName = cityLine.get(CITY_NAME);
			try {
				double latitude = Double.parseDouble(cityLine.get(CITY_LATITUDE));
				double longitude = Double.parseDouble(cityLine.get(CITY_LONGITUDE));
				int population = parseToInt(cityLine.get(POPULATION));
				RandomCity randomCity = new RandomCity(cityName, latitude, longitude, population);
				randomCities.add(randomCity);
			} catch (Exception e) {
				System.out.println("Error:" + cityLine);
			}
		}
		return randomCities;
	}

	private int parseToInt(String population) {
		if (population.length() == 0) {
			return 0;
		}
		return Integer.parseInt(population);
	}

	public List<RandomRegion> getAllRegions() {
		// TODO Auto-generated method stub
		return null;
	}

}
