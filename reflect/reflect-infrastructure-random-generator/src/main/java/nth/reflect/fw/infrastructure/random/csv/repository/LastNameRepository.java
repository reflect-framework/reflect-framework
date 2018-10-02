package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;

public class LastNameRepository extends CsvResourceFileRepository {

	public LastNameRepository() {
		super("LastNames.csv");
	}
	
	public List<String> getLastNames() {
		return getCsvColumn(0);
	}
}
