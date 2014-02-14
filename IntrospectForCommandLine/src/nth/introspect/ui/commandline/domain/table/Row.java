package nth.introspect.ui.commandline.domain.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {
	private List<Cell> cells = new ArrayList<Cell>();

	public Cell addCell(String text, int width) {
		Cell cell = new Cell(text, width);
		cells.add(cell);
		return cell;
	}

	public List<String> getLines() {
		Map<Integer, List<String>> cellLines = new HashMap<Integer, List<String>>();
		// get lines from all cells and determine max lines
		int maxLines = 0;
		for (Cell cell : cells) {
			int cellIndex = cells.indexOf(cell);
			List<String> lines = cell.getLines();
			if (lines.size() > maxLines) {
				maxLines = lines.size();
			}
			cellLines.put(cellIndex, lines);
		}

		// add lines so that all cells have the same number of lines
		for (Cell cell : cells) {
			int cellIndex = cells.indexOf(cell);
			List<String> lines = cellLines.get(cellIndex);
			for (int i = lines.size(); i < maxLines; i++) {
				lines.add(cell.getEmptyLine());
			}
		}

		// put the lines together
		List<String> lines = new ArrayList<String>();
		for (int lineIndex = 0; lineIndex < maxLines; lineIndex++) {
			StringBuffer line = new StringBuffer();
			for (Cell cell : cells) {
				int cellIndex = cells.indexOf(cell);
				String cellLine = cellLines.get(cellIndex).get(lineIndex);
				if (line.length() > 0) {
					line.append(" ");// column separator
				}
				line.append(cellLine);
			}
			lines.add(line.toString());
		}
		return lines;
	}

	@Override
	public String toString() {
		List<String> lines = getLines();

		if (lines.size() == 0) {
			// return empty line
			return "\n";
		} else {
			// return all lines
			StringBuffer row = new StringBuffer();
			for (String line : lines) {
				row.append(line);
				row.append("\n");
			}
			return row.toString();
		}
	}

}
