package nth.reflect.fw.infrastructure.random.generator.text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResourceFile {

	public class Row {

		private final String[] cells;

		public Row(String line) {
			cells = line.split(";");
		}

		public String getCell(int columnNr) {
			return cells[columnNr];
		}

	}

	private final List<String> values;

	public ResourceFile(String fileName) {
		values = readResourceFileLines(fileName);
	}

	/**
	 * reads a resource file with multiple line that have comma separated
	 * values, and gets the values of a specific column
	 * 
	 * @param fileName
	 * @param columnNr
	 */
	public ResourceFile(String fileName, int columnNr) {
		List<Row> rows = readResourceFileRows(fileName);
		values = rows.stream().map(row -> row.getCell(columnNr)).collect(Collectors.toList());
	}

	/**
	 * reads a resource file with multiple line that have comma separated
	 * values, and gets the values of a specific column, using a filter
	 * 
	 * @param fileName
	 * @param filter
	 * @param columnNr
	 */
	public ResourceFile(String fileName, Predicate<? super Row> filter, int columnNr) {
		List<Row> rows = readResourceFileRows(fileName);
		values = rows.stream().filter(filter).map(row -> row.getCell(columnNr)).collect(Collectors.toList());
	}

	private List<Row> readResourceFileRows(String fileName) {
		List<String> lines = readResourceFileLines(fileName);
		List<Row> rows = new ArrayList<>();
		for (String line : lines) {
			Row row = new Row(line);
			rows.add(row);
		}
		return rows;
	}

	private List<String> readResourceFileLines(String fileName) {
		InputStream inputStream = getClass().getResourceAsStream(fileName);
		List<String> lines=new ArrayList<>();
		if (fileName != null && !fileName.isEmpty() && inputStream != null) {
			lines=readFileLines(inputStream);
		}
		return lines;
	}

	private List<String> readFileLines(InputStream inputStream) {
		List<String> lines = new ArrayList<>();
		if (inputStream != null) {
			Scanner scanner = new Scanner(inputStream, "UTF-8");
			scanner.useDelimiter(Pattern.compile("[\\r\\n]"));
			while (scanner.hasNext()) {
				String line = scanner.next();
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}
			scanner.close();
		}
		return lines;
	}

	public List<String> getValues() {
		return values;
	}

}
