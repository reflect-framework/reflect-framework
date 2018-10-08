package nth.reflect.fw.infrastructure.random.generator.address;

public class RandomCity {
	private final String name;
	private final double latitude;
	private final double longitude;
	private final int population;

	public RandomCity(String name, double latitude, double longitude, int population) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getPopulation() {
		return population;
	}

	
}
