package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;

public class ColorNameRepository extends CsvResourceFileRepository {

	public ColorNameRepository() {
		super("ColorNames.csv");
	}
	
	public List<String> getColorNames() {
		return getCsvColumn(0);
	}
}
