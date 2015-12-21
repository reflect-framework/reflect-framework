package nth.introspect.excel.repository.exception;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;

public class CellConversionException extends ConversionError {

	public CellConversionException(Cell cell, String reason) {
		super(createMessage(cell, reason));
	}

	public CellConversionException(Cell cell, String reason, Exception exception) {
		super(createMessage(cell, reason), exception);
	}

	private static String createMessage(Cell cell, String reason) {
		StringBuilder message = new StringBuilder();
		message.append("Excel cell on worksheet: ");
		message.append(cell.getSheet().getSheetName());
		message.append(", Cell: ");
		CellReference cellReference = new CellReference(cell.getRowIndex(),
				cell.getColumnIndex());
		message.append(cellReference.formatAsString());
		message.append(", ");
		message.append(reason);
		return message.toString();
	}

	private static final long serialVersionUID = -7482274302613443489L;

}
