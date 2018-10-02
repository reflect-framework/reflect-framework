package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.fw.infrastructure.random.csv.CsvFile;
import nth.reflect.fw.infrastructure.random.csv.CsvRow;

public abstract class CsvResourceFileRepository {

	private final String csvFileName;
	private CsvFile csvFile;

	public CsvResourceFileRepository(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	/**
	 * Cashed (read once) lazy loading (only when needed) of csvFile for
	 * performance. Resource files do not change during runtime anyway.
	 */
	private CsvFile getCsvFile() {
		if (csvFile == null) {
			csvFile = new CsvFile(csvFileName);
		}
		return csvFile;
	}

	public List<String> getCsvColumn(int columnIndex) {
		CsvFile csvFile = getCsvFile();
		List<CsvRow> rows = csvFile.getRows();
		List<String> values = rows.stream().map(row -> row.getCell(columnIndex)).collect(Collectors.toList());
		return values;
	}
	
	public List<String> getCsvColumn(int columnIndex, Predicate<? super CsvRow> filter) {
		CsvFile csvFile = getCsvFile();
		List<CsvRow> rows = csvFile.getRows();
		List<String> values = rows.stream().filter(filter).map(row -> row.getCell(columnIndex)).collect(Collectors.toList());
		return values;
	}
	

}
