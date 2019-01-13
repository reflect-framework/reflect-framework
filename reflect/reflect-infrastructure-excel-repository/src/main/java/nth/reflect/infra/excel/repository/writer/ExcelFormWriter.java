package nth.reflect.infra.excel.repository.writer;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class ExcelFormWriter extends ExcelWriter {
	private ReflectionProvider reflectionProvider;

	public XSSFWorkbook write(XSSFWorkbook workbook, String title, ReflectionProvider reflectionProvider,
			Object object) {

		this.reflectionProvider = reflectionProvider;
		Sheet sheet = workbook.createSheet(title);

		initPageSetup(workbook, sheet);

		Date exportDateTime = new Date();
		initFooter(sheet, exportDateTime);

		ClassInfo classInfo = reflectionProvider.getClassInfo(object.getClass());
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAndVisibleInTable();
		int maxNumberOfColumns = getMaxNumberOfColumns(propertyInfos);

		addTitlebar(workbook, sheet, title, maxNumberOfColumns - 1);
		int rowNr = 1;

		sheet.createFreezePane(1, rowNr, 1, rowNr);

		addPropertyRows(object, sheet, propertyInfos);

		autoSizeColumns(sheet, maxNumberOfColumns);
		return workbook;
	}

	private void addPropertyRows(Object domainObject, Sheet sheet, List<PropertyInfo> propertyInfos) {
		int rowNr;
		Workbook workbook = sheet.getWorkbook();
		CellStyle PROPERTY_NAME_STYLE = getPropertyNameStyle(workbook);
		// add properties as rows
		for (PropertyInfo propertyInfo : propertyInfos) {
			if (propertyInfo.isVisibleInForm(domainObject)) {
				rowNr = sheet.getPhysicalNumberOfRows();
				Row row = sheet.createRow(rowNr);
				Cell nameCell = row.createCell(0);
				nameCell.setCellValue(propertyInfo.getDisplayName());
				nameCell.setCellStyle(PROPERTY_NAME_STYLE);

				Cell valueCell = row.createCell(1);
				setCellValue(domainObject, propertyInfo, valueCell);

				mergePropertyNameCellsifNeeded(sheet, rowNr);
			}
		}
	}

	@Override
	protected void setCellValue(Object domainObject, PropertyInfo propertyInfo, Cell cell) {

		if (propertyInfo.getTypeInfo().isCollection()) {
			Sheet sheet = cell.getSheet();
			Object value = propertyInfo.getValue(domainObject);
			addPropertyTable(sheet, cell, propertyInfo, value);
		} else {
			super.setCellValue(domainObject, propertyInfo, cell);
		}
	}

	private void addPropertyTable(Sheet sheet, Cell cell, PropertyInfo propertyInfo, Object value) {
		// TODO still used????
		Row row = cell.getRow();
		Class<?> objectClass = propertyInfo.getTypeInfo().getGenericType();

		ClassInfo classInfo = reflectionProvider.getClassInfo(objectClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAndVisibleInTable();

		createPropertyTableHeader(sheet, row, propertyInfos);

		int rowNr = row.getRowNum() + 1;
		Collection<?> collection = (Collection<?>) value;
		for (Object object : collection) {
			createPropertyTableRows(sheet, rowNr++, propertyInfos, object);
		}
	}

	private void createPropertyTableRows(Sheet sheet, int rowNr, List<PropertyInfo> propertyInfos,
			Object domainObject) {
		Cell cell;
		Row row = sheet.createRow(rowNr);
		int cellNr = 0;
		for (PropertyInfo columnInfo : propertyInfos) {
			cellNr++;
			cell = row.createCell(cellNr);
			setCellValue(domainObject, columnInfo, cell);
		}
	}

	private void createPropertyTableHeader(Sheet sheet, Row row, List<PropertyInfo> propertyInfos) {
		Cell cell;
		CellStyle COLUMN_HEADER_STYLE = createColumnHeaderStyle(sheet.getWorkbook());
		int cellNr = 0;
		for (PropertyInfo columnInfo : propertyInfos) {
			cellNr++;
			cell = row.createCell(cellNr);
			cell.setCellValue(columnInfo.getDisplayName());
			cell.setCellStyle(COLUMN_HEADER_STYLE);
		}
	}

}
