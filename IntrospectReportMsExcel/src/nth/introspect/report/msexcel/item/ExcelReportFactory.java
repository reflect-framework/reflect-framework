package nth.introspect.report.msexcel.item;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.excel.repository.RepositoryForExcel;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @deprecated use {@link RepositoryForExcel}
 * 
 * @author nilsth
 *
 */
public class ExcelReportFactory {
//	private ReflectionProvider reflectionProvider;
//	private CellStyle dateStyle;
//	private CellStyle propertyValueStyle;
//
//	public ExcelReportFactory(ReflectionProvider reflectionProvider) {
//		this.reflectionProvider = reflectionProvider;
//	}
//
//	public DownloadStream createReport(Object domainObject,
//			Class<?> domainClass, String reportTitle) {
//		//reset styles
//		propertyValueStyle=null;
//		dateStyle=null;
//		
//		// create workbook and sheet
//		Workbook wb;
//		wb = new HSSFWorkbook();
//		Sheet sheet = wb.createSheet(reportTitle);
//
//		initPageSetup(wb, sheet);
//
//		Date exportDateTime = new Date();
//		initFooter(sheet, exportDateTime);
//
//		ClassInfo classInfo = reflectionProvider.getClassInfo(domainClass);
//		List<PropertyInfo> propertyInfos = classInfo
//				.getPropertyInfosSortedAnsVisibleInTable();
//		int maxNumberOfColumns = getMaxNumberOfColumns(propertyInfos);
//
//		addTitlebar(wb, sheet, reportTitle, maxNumberOfColumns - 1);
//		int rowNr = 1;
//
//		sheet.createFreezePane(1, rowNr, 1, rowNr);
//
//		addPropertyRows(domainObject, sheet, propertyInfos);
//
//		autoSizeColumns(sheet, maxNumberOfColumns);
//
//		return createDownloadStream(reportTitle, exportDateTime, wb);
//
//	}
//
//	private void addPropertyRows(Object domainObject, Sheet sheet,
//			List<PropertyInfo> propertyInfos) {
//		int rowNr;
//		Workbook workbook = sheet.getWorkbook();
//		CellStyle PROPERTY_NAME_STYLE = createPropertyNameStyle(workbook);
//		// add properties as rows
//		for (PropertyInfo propertyInfo : propertyInfos) {
//			if (propertyInfo.isVisibleInForm(domainObject)) {
//				rowNr = sheet.getPhysicalNumberOfRows();
//				Row row = sheet.createRow(rowNr);
//				Cell nameCell = row.createCell(0);
//				nameCell.setCellValue(propertyInfo.getDisplayName());
//				nameCell.setCellStyle(PROPERTY_NAME_STYLE);
//
//				Cell valueCell = row.createCell(1);
//				setCellValue(domainObject, propertyInfo, valueCell);
//
//				mergePropertyNameCellsifNeeded(sheet, rowNr);
//			}
//		}
//	}
//
//	private void mergePropertyNameCellsifNeeded(Sheet sheet, int rowNr) {
//		int lastRowNr = sheet.getPhysicalNumberOfRows();
//		if (rowNr < lastRowNr - 1) {
//			sheet.addMergedRegion(new CellRangeAddress(rowNr, lastRowNr - 1, 0,
//					0));
//		}
//	}
//
//	private void autoSizeColumns(Sheet sheet, int lastColumn) {
//		for (int c = 0; c < lastColumn; c++) {
//			sheet.autoSizeColumn(c);
//		}
//	}
//
//	private void setCellValue(Object domainObject, PropertyInfo propertyInfo,
//			Cell cell) {
//		Sheet sheet = cell.getSheet();
//		Workbook workbook = sheet.getWorkbook();
//		CellStyle PROPERTY_VALUE_STYLE = getPropertyValueStyle(workbook);
//		CellStyle DATE_STYLE = getDateStyle(workbook);
//		cell.setCellStyle(PROPERTY_VALUE_STYLE);
//		Object value = propertyInfo.getValue(domainObject);
//		if (value == null) {
//			cell.setCellValue("");
//		} else {
//			Class<? extends Object> propertyType = value.getClass();
//			if (Integer.class.isAssignableFrom(propertyType)) {
//				cell.setCellValue(((Integer) value).doubleValue());
//			} else if (Double.class.isAssignableFrom(propertyType)) {
//				cell.setCellValue((Double) value);
//			} else if (BigDecimal.class.isAssignableFrom(propertyType)) {
//				cell.setCellValue(((BigDecimal) value).doubleValue());
//			} else if (Boolean.class.isAssignableFrom(propertyType)) {
//				cell.setCellValue((Boolean) value);
//			} else if (Date.class.isAssignableFrom(propertyType)) {
//				cell.setCellValue((Date) value);
//				cell.setCellStyle(DATE_STYLE);
//			} else if (Calendar.class.isAssignableFrom(propertyType)) {
//				cell.setCellValue((Calendar) value);
//				cell.setCellStyle(DATE_STYLE);
//			} else if (TypeCategory.COLLECTION_TYPE == propertyInfo
//					.getPropertyType().getTypeCategory()) {
//				addPropertyTable(sheet, cell, propertyInfo, value);
//			} else {
//				String formatedValue = propertyInfo
//						.getFormatedValue(domainObject);
//				cell.setCellValue(formatedValue);
//			}
//		}
//	}
//
//	private void addPropertyTable(Sheet sheet, Cell cell,
//			PropertyInfo propertyInfo, Object value) {// TODO still used????
//		Row row = cell.getRow();
//		Class<?> objectClass = propertyInfo.getPropertyType()
//				.getTypeOrGenericCollectionType();
//
//		ClassInfo classInfo = reflectionProvider.getClassInfo(objectClass);
//		List<PropertyInfo> propertyInfos = classInfo
//				.getPropertyInfosSortedAnsVisibleInTable();
//
//		createPropertyTableHeader(sheet, row, propertyInfos);
//
//		int rowNr = row.getRowNum() + 1;
//		Collection<?> collection = (Collection<?>) value;
//		for (Object object : collection) {
//			createPropertyTableRows(sheet, rowNr++, propertyInfos, object);
//		}
//	}
//
//	private void createPropertyTableHeader(Sheet sheet, Row row,
//			List<PropertyInfo> propertyInfos) {
//		Cell cell;
//		CellStyle COLUMN_HEADER_STYLE = createColumnHeaderStyle(sheet
//				.getWorkbook());
//		int cellNr = 0;
//		for (PropertyInfo columnInfo : propertyInfos) {
//			cellNr++;
//			cell = row.createCell(cellNr);
//			cell.setCellValue(columnInfo.getDisplayName());
//			cell.setCellStyle(COLUMN_HEADER_STYLE);
//		}
//	}
//
//	private void createPropertyTableRows(Sheet sheet, int rowNr,
//			List<PropertyInfo> propertyInfos, Object domainObject) {
//		Cell cell;
//		Row row = sheet.createRow(rowNr);
//		int cellNr = 0;
//		for (PropertyInfo columnInfo : propertyInfos) {
//			cellNr++;
//			cell = row.createCell(cellNr);
//			setCellValue(domainObject, columnInfo, cell);
//		}
//	}
//
//	private int getMaxNumberOfColumns(List<PropertyInfo> propertyInfos) {
//		int maxNumberOfColumns = 1;// single propertyValue
//		for (PropertyInfo propertyInfo : propertyInfos) {
//			if (TypeCategory.COLLECTION_TYPE == propertyInfo.getPropertyType()
//					.getTypeCategory()) {
//				// property is displayed as a table
//				int numberOfColumns = propertyInfos.size();
//				if (numberOfColumns > maxNumberOfColumns) {
//					maxNumberOfColumns = numberOfColumns;
//				}
//			}
//		}
//		maxNumberOfColumns++; // add one column number for property names
//		return maxNumberOfColumns;
//	}
//
//	private void addTitlebar(Workbook wb, Sheet sheet, String reportTitle,
//			int maxNumberOfColumns) {
//		CellStyle HEADER_TITLE_STYLE = createTitleBarStyle(wb);
//		Row row = sheet.createRow(0);
//		row.setHeightInPoints(25);
//		Cell titleCell = row.createCell(0);
//		titleCell.setCellValue(reportTitle);
//		titleCell.setCellStyle(HEADER_TITLE_STYLE);
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, maxNumberOfColumns));
//	}
//
//	public DownloadStream createReport(Collection<?> domainObjects,
//			Class<?> domainClass, String reportTitle) {
//
//		Date exportDateTime = new Date();
//
//		List<PropertyInfo> propertyInfos = getPropertyInfosForTable(domainClass);
//
//		Workbook wb;
//		wb = new HSSFWorkbook();
//		Sheet sheet = wb.createSheet(reportTitle);
//
//		initPageSetup(wb, sheet);
//
//		initFooter(sheet, exportDateTime);
//
//		int nr_of_columns = propertyInfos.size();
//		addTitlebar(wb, sheet, reportTitle, nr_of_columns - 1);
//
//		addTableHeaderRow(propertyInfos, sheet);
//
//		int rowNr = sheet.getPhysicalNumberOfRows();
//
//		sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, nr_of_columns - 1));
//
//		sheet.createFreezePane(0, rowNr, 0, rowNr);
//
//		addDomainObjectRows(domainObjects, propertyInfos, sheet);
//
//		autoSizeColumns(sheet, nr_of_columns - 1);
//
//		return createDownloadStream(reportTitle, exportDateTime, wb);
//
//	}
//
//	private List<PropertyInfo> getPropertyInfosForTable(Class<?> domainClass) {
//		// get propertyInfos
//		ClassInfo classInfo = reflectionProvider.getClassInfo(domainClass);
//		List<PropertyInfo> propertyInfos = classInfo
//				.getPropertyInfosSortedAnsVisibleInTable();
//		return propertyInfos;
//	}
//
//	private int addDomainObjectRows(Collection<?> domainObjects,
//			List<PropertyInfo> propertyInfos, Sheet sheet) {
//		Row row;
//		int columnNr;
//		Cell cell;
//		int rowNr = sheet.getPhysicalNumberOfRows();
//		for (Object domainObject : domainObjects) {
//			row = sheet.createRow(rowNr++);
//			columnNr = 0;
//			for (PropertyInfo propertyInfo : propertyInfos) {
//				cell = row.createCell(columnNr++);
//				setCellValue(domainObject, propertyInfo, cell);
//			}
//		}
//		return rowNr;
//	}
//
//	private void addTableHeaderRow(List<PropertyInfo> propertyInfos, Sheet sheet) {
//		Workbook workbook = sheet.getWorkbook();
//		CellStyle HEADER_COLUMNS_STYLE = createColumnHeaderStyle(workbook);
//		int rowNr = sheet.getPhysicalNumberOfRows();
//		Row row = sheet.createRow(rowNr);
//		int columnNr = 0;
//		Cell cell;
//		for (PropertyInfo propertyInfo : propertyInfos) {
//			cell = row.createCell(columnNr++);
//			String columnHeaderText = propertyInfo.getDisplayName();
//			cell.setCellValue(columnHeaderText);
//			cell.setCellStyle(HEADER_COLUMNS_STYLE);
//		}
//	}
//
//	private DownloadStream createDownloadStream(String reportTitle,
//			Date exportDateTime, Workbook workbook) {
//		// send stream
//		try {
//			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//			workbook.write(buffer);
//			InputStream inputStream = new ByteArrayInputStream(
//					buffer.toByteArray());
//			String filePath = getFilePath(reportTitle, exportDateTime);
//			File file = new File(filePath);
//			DownloadStream downloadStream = new DownloadStream(file,
//					inputStream);
//			return downloadStream;
//		} catch (Exception e) {
//			throw new RuntimeException("Error creating excel report", e);
//		}
//	}
//
//	private void initPageSetup(Workbook wb, Sheet sheet) {
//		initPageSetup(sheet);
//		// repeat header when printing
//		wb.setRepeatingRowsAndColumns(0, 0, 0, 0, 1);
//	}
//
//	private String getFilePath(String reportTitle, Date creationDate) {
//		SimpleDateFormat format = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
//		StringBuilder filePath = new StringBuilder();
//		filePath.append(reportTitle);
//		filePath.append(format.format(creationDate));
//		filePath.append(".xls");
//		return filePath.toString();
//	}
//
//	private CellStyle getDateStyle(Workbook wb) {
//		if (dateStyle == null) {
//			dateStyle = createDateStyle(wb);
//		}
//		return dateStyle;
//	}
//
//	private CellStyle createDateStyle(Workbook wb) {
//		CreationHelper createHelper = wb.getCreationHelper();
//		CellStyle cellStyle = wb.createCellStyle();
//		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
//				"yyyy-MM-dd HH:mm:ss"));
//		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
//		return cellStyle;
//	}
//
//	private CellStyle createTitleBarStyle(Workbook wb) {
//		CellStyle style = wb.createCellStyle();
//		style.setAlignment(CellStyle.ALIGN_CENTER);
//		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		Font font = wb.createFont();
//		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//		font.setFontHeight((short) (font.getFontHeight() * 1.5));
//		style.setFont(font);
//		return style;
//	}
//
//	private CellStyle createPropertyNameStyle(Workbook wb) {
//		CellStyle style = wb.createCellStyle();
//		style.setAlignment(CellStyle.ALIGN_RIGHT);
//		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		return style;
//	}
//
//	private CellStyle getPropertyValueStyle(Workbook wb) {
//		if (propertyValueStyle == null) {
//			propertyValueStyle = createPropertyValueStyle(wb);
//		}
//		return propertyValueStyle;
//	}
//
//	private CellStyle createPropertyValueStyle(Workbook wb) {
//		CellStyle style = wb.createCellStyle();
//		style.setAlignment(CellStyle.ALIGN_LEFT);
//		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//		return style;
//	}
//
//	private CellStyle createColumnHeaderStyle(Workbook wb) {
//		CellStyle style = wb.createCellStyle();
//		style.setAlignment(CellStyle.ALIGN_CENTER);
//		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		Font font = wb.createFont();
//		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//		style.setFont(font);
//		style.setBorderTop(CellStyle.BORDER_THIN);
//		style.setBorderRight(CellStyle.BORDER_THIN);
//		style.setBorderBottom(CellStyle.BORDER_THIN);
//		style.setBorderLeft(CellStyle.BORDER_THIN);
//		return style;
//	}
//
//	
//	
//	private void initPageSetup(Sheet sheet) {
//		// print and page setup
//		sheet.setPrintGridlines(true);
//		sheet.setDisplayGridlines(true);
//		sheet.setAutobreaks(true);
//		sheet.setHorizontallyCenter(true);
//		// set content size when printing
//		PrintSetup printSetup = sheet.getPrintSetup();
//		sheet.setFitToPage(true);
//		printSetup.setFitHeight(Short.MAX_VALUE);
//		printSetup.setFitWidth((short) 1);
//	}
//
//	private void initFooter(Sheet sheet, Date exportDateTime) {
//		Footer footer = sheet.getFooter();
//		// add a fixed export date and time (not a dynamic HeaderFooter.date() )
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		footer.setLeft(dateFormat.format(exportDateTime));
//		footer.setRight("Page " + HeaderFooter.page() + " of "
//				+ HeaderFooter.numPages());
//	}
//
}
