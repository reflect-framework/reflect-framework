package nth.introspect.excel.repository.exception;

import java.io.File;

import org.apache.poi.ss.usermodel.Sheet;

public class NoRowWithColumnNamesException extends Exception {

	private static final long serialVersionUID = -2154045421908997068L;
	private static final String NEW_LINE = "\n";
	
	public NoRowWithColumnNamesException(File excelFile,
			Sheet sheet, String[] columnNames) {
		super(createMessage(excelFile, sheet, columnNames));
	}

	private static String createMessage(File excelFile, Sheet sheet,
			String[] columnNames) {
		StringBuilder message=new StringBuilder();
		message.append("Could not find a row that contains all column names: ");

		boolean first=true;
		for (String columnName : columnNames) {
			if (first) {
				first=false;
			} else {
				message.append(", ");	
			}
			message.append(columnName);
			
		}
		message.append(NEW_LINE);
		message.append("File: ");
		message.append(excelFile.getAbsolutePath());
		message.append(NEW_LINE);
		message.append("Sheet: ");
		message.append(sheet.getSheetName());
		return message.toString();
	}

}
