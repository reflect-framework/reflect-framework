package nth.reflect.infra.excel.repository.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nth.reflect.infra.excel.repository.exception.CellConversionException;
import nth.reflect.infra.excel.repository.exception.CouldNotFindSheetsException;
import nth.reflect.infra.excel.repository.sheetfilter.SheetFilter;

public abstract class ExcelReader<T> {

	protected abstract File getExcelFile() throws Exception ;

	public List<T> readAll() throws Exception {
		File excelFile=getExcelFile();
		if (!excelFile.exists()) {
			throw new FileNotFoundException(excelFile.getAbsolutePath());
		}
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		List<T> objectsFromWorkbook=new ArrayList<>();
		List<Sheet> sheets = findSheets(workbook, excelFile);
		for (Sheet sheet : sheets) {
			List<T> objectsFromSheet=readSheet(excelFile, sheet);
			objectsFromWorkbook.addAll(objectsFromSheet);
		} 
		return objectsFromWorkbook;
	}


	private List<Sheet> findSheets(XSSFWorkbook workbook, File excelFile)
			throws CouldNotFindSheetsException {
		List<Sheet> foundSheets = new ArrayList<>();
		SheetFilter sheetFilter=getSheetFilter();
		for (int sheetNr = 0; sheetNr < workbook.getNumberOfSheets(); sheetNr++) {
			Sheet sheet = workbook.getSheetAt(sheetNr);
			
			if (sheetFilter.test(sheet)) {
				foundSheets.add(sheet);
			}
		}
		if (foundSheets.isEmpty()) {
			throw new CouldNotFindSheetsException(excelFile);
		}
		return foundSheets;
	}
	

	public abstract  SheetFilter getSheetFilter();

	public abstract  List<T> readSheet(File excelFile, Sheet sheet) throws Exception;

	
	public String getString(Row row, Integer columnIndex) {
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			return Integer.toString((int) cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
		case Cell.CELL_TYPE_FORMULA:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_ERROR:
			return null;
		}
		throw new CellConversionException(cell,
				"could not be parsed to a String");
	}


	
	public Integer getInteger(Row row, Integer columnIndex) {
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			return null;
		}
		try {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return (int) cell.getNumericCellValue();
			case Cell.CELL_TYPE_STRING:
			case Cell.CELL_TYPE_FORMULA:
				return Integer.parseInt(cell.getStringCellValue());
			case Cell.CELL_TYPE_BLANK:
				return null;
			case Cell.CELL_TYPE_ERROR:
				return null;
			}
		} catch (Exception exception) {
			throw new CellConversionException(cell,
					"could not be parsed to a Integer", exception);
		}
		throw new CellConversionException(cell,
				"could not be parsed to a Integer");
	}


	
	public Date getDate(Row row, Integer columnIndex) {
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			return null;
		}

		try {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getDateCellValue();
			case Cell.CELL_TYPE_STRING:
				String dateString = cell.getStringCellValue();
				if (dateString.trim().length() == 0) {
					return null;
				}
				if (dateString.length() == 4) {
					int year = Integer.parseInt(dateString);
					Calendar calendar=Calendar.getInstance();
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DAY_OF_MONTH, 0);
					calendar.set(Calendar.HOUR, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);
					Date date = calendar.getTime();
					return date;
				}
				return new SimpleDateFormat().parse(dateString);
			}
		} catch (Exception exception) {
			throw new CellConversionException(cell,
					"could not be parsed to a Date", exception);
		}
		throw new CellConversionException(cell, "could not be parsed to a Date");
	}


	
	public Double getDouble(Row row, Integer columnIndex) {
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
		case Cell.CELL_TYPE_FORMULA:
			return cell.getNumericCellValue();
		case Cell.CELL_TYPE_STRING:
			try {
				String string = cell.getStringCellValue();
				return Double.valueOf(string);
			} catch (Exception e) {
				throw new CellConversionException(cell,
						"could not be parsed to a Double", e);
			}
		case Cell.CELL_TYPE_BLANK:
		case Cell.CELL_TYPE_ERROR:
			return null;
		}
		throw new CellConversionException(cell,
				"could not be parsed to a Double");
	}


	
	public String getFormula(Row row, Integer columnIndex) {
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_ERROR:
			return null;
		}
		throw new CellConversionException(cell,
				"could not be parsed to a Formula");
	}

}
