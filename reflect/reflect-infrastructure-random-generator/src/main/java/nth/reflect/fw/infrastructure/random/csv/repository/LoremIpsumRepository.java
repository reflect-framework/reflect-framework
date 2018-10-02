package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;

public class LoremIpsumRepository extends CsvResourceFileRepository {

	public LoremIpsumRepository() {
		super("LoremIpsum.csv");
	}
	
	public List<String> getLoremIpsumWords() {
		return getCsvColumn(0);
	}
}
