package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;

public class FemaleNameRepository extends CsvResourceFileRepository {

	public FemaleNameRepository() {
		super("FemaleNames.csv");
	}
	
	public List<String> getFemaleNames() {
		return getCsvColumn(0);
	}

}
