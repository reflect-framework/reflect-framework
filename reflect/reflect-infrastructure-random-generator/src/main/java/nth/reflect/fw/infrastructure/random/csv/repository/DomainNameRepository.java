package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;

public class DomainNameRepository extends CsvResourceFileRepository {

	public DomainNameRepository() {
		super("DomainNames.csv");
	}
	
	public List<String> getDomainNames() {
		return getCsvColumn(0);
	}
}
