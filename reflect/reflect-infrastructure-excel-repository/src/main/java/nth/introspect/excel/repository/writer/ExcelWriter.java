package nth.introspect.excel.repository.writer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

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

public abstract class ExcelWriter {

	private CellStyle propertyValueStyle;
	private CellStyle propertyNameStyle;
	private CellStyle dateStyle;

	protected int getMaxNumberOfColumns(List<PropertyInfo> propertyInfos) {
		int maxNumberOfColumns = 1;// single propertyValue
		for (PropertyInfo propertyInfo : propertyInfos) {
			if (TypeCategory.COLLECTION_TYPE == propertyInfo.getPropertyType()
					.getTypeCategory()) {
				// property is displayed as a table
				int numberOfColumns = propertyInfos.size();
				if (numberOfColumns > maxNumberOfColumns) {
					maxNumberOfColumns = numberOfColumns;
				}
			}
		}
		maxNumberOfColumns++; // add one column number for property names
		return maxNumberOfColumns;
	}

	protected void addTitlebar(Workbook wb, Sheet sheet, String reportTitle,
			int maxNumberOfColumns) {
		CellStyle HEADER_TITLE_STYLE = createTitleBarStyle(wb);
		Row row = sheet.createRow(0);
		row.setHeightInPoints(25);
		Cell titleCell = row.createCell(0);
		titleCell.setCellValue(reportTitle);
		titleCell.setCellStyle(HEADER_TITLE_STYLE);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, maxNumberOfColumns));
	}
	
	private CellStyle createTitleBarStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeight((short) (font.getFontHeight() * 1.5));
		style.setFont(font);
		return style;
	}

	

	public CellStyle getPropertyNameStyle(Workbook workbook) {
		if (propertyNameStyle==null) {
			propertyNameStyle=createPropertyNameStyle(workbook);
		}
		return propertyNameStyle;
	}

	
	private CellStyle createPropertyNameStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	public CellStyle getPropertyValueStyle(Workbook wb) {
		if (propertyValueStyle == null) {
			propertyValueStyle = createPropertyValueStyle(wb);
		}
		return propertyValueStyle;
	}

	private CellStyle createPropertyValueStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	protected CellStyle createColumnHeaderStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		return style;
	}
	
	protected CellStyle getDateStyle(Workbook wb) {
		if (dateStyle == null) {
			dateStyle = createDateStyle(wb);
		}
		return dateStyle;
	}

	private CellStyle createDateStyle(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
				"yyyy-MM-dd HH:mm:ss"));
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		return cellStyle;
	}

	protected void initPageSetup(Sheet sheet) {
		// print and page setup
		sheet.setPrintGridlines(true);
		sheet.setDisplayGridlines(true);
		sheet.setAutobreaks(true);
		sheet.setHorizontallyCenter(true);
		// set content size when printing
		PrintSetup printSetup = sheet.getPrintSetup();
		sheet.setFitToPage(true);
		printSetup.setFitHeight(Short.MAX_VALUE);
		printSetup.setFitWidth((short) 1);
	}
	
	@SuppressWarnings("deprecation")
	protected void initPageSetup(Workbook wb, Sheet sheet) {
		initPageSetup(sheet);
		// repeat header when printing
		
		//sheet.setRepeatingRows(new CellRangeAddress(0, 1, 0, 0));
		
		wb.setRepeatingRowsAndColumns(0, 0, 0, 0, 1);
	}

	protected void initFooter(Sheet sheet, Date exportDateTime) {
		Footer footer = sheet.getFooter();
		// add a fixed export date and time (not a dynamic HeaderFooter.date() )
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		footer.setLeft(dateFormat.format(exportDateTime));
		footer.setRight("Page " + HeaderFooter.page() + " of "
				+ HeaderFooter.numPages());
	}

	protected void autoSizeColumns(Sheet sheet, int lastColumn) {
		for (int c = 0; c < lastColumn; c++) {
			sheet.autoSizeColumn(c);
		}
	}
	
	protected void mergePropertyNameCellsifNeeded(Sheet sheet, int rowNr) {
		int lastRowNr = sheet.getPhysicalNumberOfRows();
		if (rowNr < lastRowNr - 1) {
			sheet.addMergedRegion(new CellRangeAddress(rowNr, lastRowNr - 1, 0,
					0));
		}
	}

	protected void setCellValue(Object domainObject, PropertyInfo propertyInfo,
			Cell cell) {
		Sheet sheet = cell.getSheet();
		Workbook workbook = sheet.getWorkbook();
		CellStyle PROPERTY_VALUE_STYLE = getPropertyValueStyle(workbook);
		CellStyle DATE_STYLE = getDateStyle(workbook);
		cell.setCellStyle(PROPERTY_VALUE_STYLE);
		Object value = propertyInfo.getValue(domainObject);
		if (value == null) {
			cell.setCellValue("");
		} else {
			Class<? extends Object> propertyType = value.getClass();
			if (Integer.class.isAssignableFrom(propertyType)) {
				cell.setCellValue(((Integer) value).doubleValue());
			} else if (Double.class.isAssignableFrom(propertyType)) {
				cell.setCellValue((Double) value);
			} else if (BigDecimal.class.isAssignableFrom(propertyType)) {
				cell.setCellValue(((BigDecimal) value).doubleValue());
			} else if (Boolean.class.isAssignableFrom(propertyType)) {
				cell.setCellValue((Boolean) value);
			} else if (Date.class.isAssignableFrom(propertyType)) {
				cell.setCellValue((Date) value);
				cell.setCellStyle(DATE_STYLE);
			} else if (Calendar.class.isAssignableFrom(propertyType)) {
				cell.setCellValue((Calendar) value);
				cell.setCellStyle(DATE_STYLE);
			} else {
				String formatedValue = propertyInfo
						.getFormatedValue(domainObject);
				cell.setCellValue(formatedValue);
			}
		}
	}
	
	
	
}
