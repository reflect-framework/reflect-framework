package nth.reflect.fw.infrastructure.random.csv;

public class CsvRow {

	private final String[] cells;

	public CsvRow(String line) {
		cells = line.split(";");
	}

	public String getCell(int columnNr) {
		return cells[columnNr];
	}

}