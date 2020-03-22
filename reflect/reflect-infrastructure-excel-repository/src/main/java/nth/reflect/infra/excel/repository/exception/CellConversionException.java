package nth.reflect.infra.excel.repository.exception;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CellConversionException extends ConversionError {

	private static final long serialVersionUID = -7482274302613443489L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			CellConversionException.class.getCanonicalName() + ".message", "Excel cell on worksheet: %s, Cell: %s, %s");

	public CellConversionException(Cell cell, String reason) {
		super(MESSAGE.withParameters(cell, reason));
	}

	public CellConversionException(Cell cell, String reason, Exception exception) {
		super(MESSAGE.withParameters(cell.getSheet().getSheetName(),
				new CellReference(cell.getRowIndex(), cell.getColumnIndex()).formatAsString(), reason), exception);
	}
}
