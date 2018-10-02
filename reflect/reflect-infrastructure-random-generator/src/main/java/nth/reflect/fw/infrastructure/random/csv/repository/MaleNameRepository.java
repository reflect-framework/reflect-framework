package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;

public class MaleNameRepository extends CsvResourceFileRepository {

	public MaleNameRepository() {
		super("MaleNames.csv");
	}
	
	public List<String> getMaleNames() {
		return getCsvColumn(0);
	}

}
