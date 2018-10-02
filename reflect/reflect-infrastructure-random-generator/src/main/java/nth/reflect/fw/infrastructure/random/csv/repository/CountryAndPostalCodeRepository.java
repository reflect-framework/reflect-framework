package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.infrastructure.random.csv.CsvRow;

public class CountryAndPostalCodeRepository extends CsvResourceFileRepository{

	private static final int COUNTRY_COLUMN = 1;
	private static final int POSTAL_CODES_COLUMN = 2;

	public CountryAndPostalCodeRepository() {
		super("Countries-PostalCodes.csv");
	}
	
	public List<String> getCountries() {
		return getCsvColumn(COUNTRY_COLUMN);
	}
	
	public List<String> getPostalCodes() {
		return getCsvColumn(POSTAL_CODES_COLUMN);
	}
	
	public List<String> getPostalCodes(String country) {
		String countryFind = country.trim().toLowerCase();
		Predicate<? super CsvRow> filter = row -> row.getCell(COUNTRY_COLUMN ).trim().toLowerCase()
				.equals(countryFind);
		return getCsvColumn(POSTAL_CODES_COLUMN, filter);
	}
	
	
}
