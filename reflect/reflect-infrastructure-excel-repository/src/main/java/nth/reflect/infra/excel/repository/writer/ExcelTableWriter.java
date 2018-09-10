package nth.reflect.infra.excel.repository.writer;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class ExcelTableWriter extends ExcelWriter {


	public XSSFWorkbook write(XSSFWorkbook workbook, String title, ReflectionProvider reflectionProvider, Class<?> objectType, Collection<?> objects) {
		Date exportDateTime = new Date();

		List<PropertyInfo> propertyInfos = getPropertyInfosForTable(reflectionProvider, objectType);

		Sheet sheet = workbook.createSheet(title);

		initPageSetup(workbook, sheet);

		initFooter(sheet, exportDateTime);

		int nr_of_columns = propertyInfos.size();
		addTitlebar(workbook, sheet, title, nr_of_columns - 1);

		addTableHeaderRow(propertyInfos, sheet);

		int rowNr = sheet.getPhysicalNumberOfRows();

		sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, nr_of_columns - 1));

		sheet.createFreezePane(0, rowNr, 0, rowNr);

		addDomainObjectRows(objects, propertyInfos, sheet);

		autoSizeColumns(sheet, nr_of_columns - 1);
		return workbook;
	}
	
	private List<PropertyInfo> getPropertyInfosForTable(ReflectionProvider reflectionProvider, Class<?> domainClass) {
		// get propertyInfos
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = classInfo
				.getPropertyInfosSortedAndVisibleInTable();
		return propertyInfos;
	}
	

	private void addTableHeaderRow(List<PropertyInfo> propertyInfos, Sheet sheet) {
		Workbook workbook = sheet.getWorkbook();
		CellStyle HEADER_COLUMNS_STYLE = createColumnHeaderStyle(workbook);
		int rowNr = sheet.getPhysicalNumberOfRows();
		Row row = sheet.createRow(rowNr);
		int columnNr = 0;
		Cell cell;
		for (PropertyInfo propertyInfo : propertyInfos) {
			cell = row.createCell(columnNr++);
			String columnHeaderText = propertyInfo.getDisplayName();
			cell.setCellValue(columnHeaderText);
			cell.setCellStyle(HEADER_COLUMNS_STYLE);
		}
	}


	private int addDomainObjectRows(Collection<?> domainObjects,
			List<PropertyInfo> propertyInfos, Sheet sheet) {
		Row row;
		int columnNr;
		Cell cell;
		int rowNr = sheet.getPhysicalNumberOfRows();
		for (Object domainObject : domainObjects) {
			row = sheet.createRow(rowNr++);
			columnNr = 0;
			for (PropertyInfo propertyInfo : propertyInfos) {
				cell = row.createCell(columnNr++);
				setCellValue(domainObject, propertyInfo, cell);
			}
		}
		return rowNr;
	}

	
}
