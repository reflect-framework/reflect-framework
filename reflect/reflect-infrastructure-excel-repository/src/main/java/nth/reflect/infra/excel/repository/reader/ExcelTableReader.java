package nth.reflect.infra.excel.repository.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import nth.reflect.infra.excel.repository.exception.ColumnNamesNotFoundException;
import nth.reflect.infra.excel.repository.exception.NoRowWithColumnNamesException;

public abstract class ExcelTableReader<T> extends ExcelReader<T> {

	private static final int COLUMN_NAME_NOT_FOUND = -1;
	private Map<String, Integer> columnIndexes;


	@Override
	public List<T> readSheet(File excelFile, Sheet sheet) throws NoRowWithColumnNamesException, ColumnNamesNotFoundException {
		Row headerRow = findHeaderRow(excelFile, sheet);
		columnIndexes = findColumnIndexes(headerRow);
		int firstRowNr = headerRow.getRowNum() + 1;
		int lastRowNr = sheet.getPhysicalNumberOfRows();
		List<T> objects = new ArrayList<T>();
		for (int rowNr = firstRowNr; rowNr < lastRowNr; rowNr++) {
			Row row = sheet.getRow(rowNr);
			if (row != null) {
				T object = readRow(row, columnIndexes);
				if (object != null) {
					objects.add(object);
				}
			}
		}
		return objects;
	}

	public abstract T readRow(Row row, Map<String, Integer> columnIndexes) ;

	private Row findHeaderRow(File excelFile,Sheet sheet)
			throws NoRowWithColumnNamesException, ColumnNamesNotFoundException {
		String[] columnNames = getColumnNames();
		Iterator<Row> rowIterator = sheet.rowIterator();
		Map<Row, List<String>> candidates = new HashMap<>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			List<String> missingColumnNames = getMissingColumnNames(row);
			if (missingColumnNames.isEmpty()) {
				return row;
			}
			if (missingColumnNames.size() < columnNames.length) {
				candidates.put(row, missingColumnNames);
			}
		}
		if (candidates.size() == 0) {
			throw new NoRowWithColumnNamesException(excelFile, sheet, columnNames);
		} else {
			throw new ColumnNamesNotFoundException(excelFile, sheet, candidates);
		}
	}

	public abstract  String[] getColumnNames();

	private List<String> getMissingColumnNames(Row row) {
		List<String> missingColumnNames = new ArrayList<>();
		String[] columnNames = getColumnNames();
		for (String columnName : columnNames) {
			int columnIndex = findColumnIndex(row, columnName);
			if (columnIndex == COLUMN_NAME_NOT_FOUND) {
				missingColumnNames.add(columnName);
			}
		}
		return missingColumnNames;
	}

	private Map<String, Integer> findColumnIndexes(Row headerRow)
			throws ColumnNamesNotFoundException {
		Map<String, Integer> columnIndexes = new HashMap<String, Integer>();
		String[] columnNames = getColumnNames();
		for (String columnName : columnNames) {
			int columnIndex = findColumnIndex(headerRow, columnName);
			columnIndexes.put(columnName, columnIndex);
		}
		return columnIndexes;
	}

	private int findColumnIndex(Row headerRow, String columnName) {
		columnName = columnName.toLowerCase();
		for (int columnIndex = headerRow.getFirstCellNum(); columnIndex < headerRow
				.getLastCellNum(); columnIndex++) {
			String cellValue = getString(headerRow, columnIndex);
			if (cellValue != null && columnName.equals(cellValue.toLowerCase())) {
				return columnIndex;
			}
		}
		return COLUMN_NAME_NOT_FOUND;
	}

	public String getString(Row row, String columnName) {
		return getString(row, columnIndexes.get(columnName));
	}

	public Integer getInteger(Row row, String columnName) {
		return getInteger(row, columnIndexes.get(columnName));
	}
	
	
	public Date getDate(Row row, String columnName) {
		return getDate(row, columnIndexes.get(columnName));
	}
	
	public Double getDouble(Row row, String columnName) {
		return getDouble(row, columnIndexes.get(columnName));
		
	}
	
	public String getFormula(Row row, String columnName) {
		return getFormula(row, columnIndexes.get(columnName));
	}
}
