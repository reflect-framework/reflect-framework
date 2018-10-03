package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.fw.infrastructure.random.csv.CsvRow;

public class CountryAndPostalCodeRepository extends CsvResourceFileRepository{

	private static final int COUNTRY_COLUMN = 1;
	private static final int POSTAL_CODE_FORMATS_COLUMN = 2;

	public CountryAndPostalCodeRepository() {
		super("Countries-PostalCodes.csv");
	}
	
	public List<String> getCountries() {
		return getCsvColumn(COUNTRY_COLUMN);
	}
	
	public List<String> getPostalCodeFormats() {
		return getCsvColumn(POSTAL_CODE_FORMATS_COLUMN).stream().filter(f-> !f.equals("-")).collect(Collectors.toList());
	}
	
	public List<String> getPostalCodeFormats(String country) {
		String countryFind = country.trim().toLowerCase();
		Predicate<? super CsvRow> filter = row -> (row.getCell(COUNTRY_COLUMN ).trim().toLowerCase()
				.equals(countryFind)&& !"-".equals(row.getCell(POSTAL_CODE_FORMATS_COLUMN)));
		return getCsvColumn(POSTAL_CODE_FORMATS_COLUMN, filter);
	}
	
	
}
