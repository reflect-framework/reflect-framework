package nth.introspect.excel.repository.exception;

import java.io.File;

public class CouldNotFindSheetsException extends Exception {

	private static final long serialVersionUID = 3418189532212314619L;

	public CouldNotFindSheetsException(File excelFile) {
		super(getMessage(excelFile));
	}

	private static String getMessage(File excelFile) {
		StringBuilder message = new StringBuilder();
		message.append("Could not find an matching sheet in: ");
		message.append(excelFile.getAbsolutePath());
		return message.toString();
	}

}
