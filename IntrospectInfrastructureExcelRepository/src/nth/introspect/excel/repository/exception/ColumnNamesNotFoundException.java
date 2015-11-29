package nth.introspect.excel.repository.exception;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ColumnNamesNotFoundException extends Exception {

	private static final long serialVersionUID = -6638114702168864481L;
	private static final String NEW_LINE = "\n";

	public ColumnNamesNotFoundException(File excelFile, Sheet sheet,
			Map<Row, List<String>> candidates) {
		super(createMessage(excelFile, sheet, candidates));
	}

	private static String createMessage(File excelFile, Sheet sheet,
			Map<Row, List<String>> candidates) {
		
		Row row = findCandidateWithMostColumnNames(candidates);
		List<String> missingColumnNames = candidates.get(row);
		
		StringBuilder message=new StringBuilder();
		if (missingColumnNames.size()==1) {
			message.append("Could not find a column named: ");
			message.append(missingColumnNames.get(0));
		} else {
			message.append("Could not find a column names: ");
			message.append(missingColumnNames);
		}
		message.append(NEW_LINE);
		message.append("File: ");
		message.append(excelFile.getAbsolutePath());
		message.append(NEW_LINE);
		message.append("Sheet: ");
		message.append(sheet.getSheetName());
		message.append(NEW_LINE);
		message.append("Row: ");
		message.append(row.getRowNum());
		return message.toString();
	}

	
	private static Row findCandidateWithMostColumnNames(
			Map<Row, List<String>> candidates) {
		Row bestCandidate=null;
		for (Row row:candidates.keySet()) {
			if (bestCandidate==null) {
				bestCandidate=row;
			} else {
				int bestCandidateNumberMissing = candidates.get(bestCandidate).size();
				int rowCandidateNumberMissing = candidates.get(row).size();

				if (bestCandidateNumberMissing >rowCandidateNumberMissing) {
					bestCandidate=row;
				}
			}
		}
		return bestCandidate;
	}
	
}
