package nth.reflect.fw.infrastructure.random.csv;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CsvFile {

	private List<CsvRow> rows;

	public CsvFile(String fileName) {
		rows=readResourceFileRows(fileName);
	}

	private List<CsvRow> readResourceFileRows(String fileName) {
		List<String> lines = readResourceFileLines(fileName);
		List<CsvRow> rows = new ArrayList<>();
		for (String line : lines) {
			CsvRow row = new CsvRow(line);
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
		if (lines.size()==0) {
			throw new RuntimeException("Could not read resource file: "+ fileName);
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


	public List<CsvRow> getRows() {
		return rows;
	}

}
