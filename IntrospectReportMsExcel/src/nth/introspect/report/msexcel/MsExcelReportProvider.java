package nth.introspect.report.msexcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.property.TableOrderComparator;
import nth.introspect.provider.domain.info.property.TableVisibleFilter;
import nth.introspect.provider.report.FormSection;
import nth.introspect.provider.report.Report;
import nth.introspect.provider.report.ReportProvider;
import nth.introspect.provider.report.Section;
import nth.introspect.provider.report.TableSection;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class MsExcelReportProvider extends ReportProvider<Workbook> {

	private CellStyle dateStyle;
	private CellStyle columnHeaderStyle;

	@Override
	public Workbook createDocument(Report report) {
		// create workbook and sheet
		Workbook workbook;
		workbook = new HSSFWorkbook();
		// repeat header when printing
		workbook.setRepeatingRowsAndColumns(0, 0, 0, 0, 1);
		return workbook;
	}

	@Override
	public void addFormSection(Workbook workbook, Report report, FormSection formSection) {
		Sheet sheet = createSheet(report, formSection, workbook);

		int MAX_NUMBER_OF_COLUMNS = 2;
		// only using 2 columns. hide remaining columns
		for (int c = MAX_NUMBER_OF_COLUMNS; c < 256; c++) {
			sheet.setColumnHidden(c, true);
		}

		// header title
		int rowNr = 0;
		// Row row = sheet.createRow(rowNr++);
		// row.setHeightInPoints(25);
		// Cell titleCell = row.createCell(0);
		// titleCell.setCellValue(title);
		// titleCell.setCellStyle(HEADER_TITLE_STYLE);
		// sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, MAX_NUMBER_OF_COLUMNS));

		// Freeze header rows
		sheet.createFreezePane(0, rowNr, 0, rowNr);

		// get domain object
		Object domainObject = formSection.getIntrospectedObject();

		// add properties as rows
		DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		List<PropertyInfo> propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(domainObject.getClass(), propertyInfoFilter, propertyInfoComparator);
		for (PropertyInfo propertyInfo : propertyInfos) {
			Row row = sheet.createRow(rowNr++);
			Cell cell = row.createCell(0);
			cell.setCellValue(propertyInfo.getText());// TODO allign left, see createHeaderColumnsStype
			cell.setCellStyle(getPropertyNameStyle());

			cell = row.createCell(1);
			// put value in the cell with the right type
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
					cell.setCellStyle(getDateStyle(workbook));
				} else if (Calendar.class.isAssignableFrom(propertyType)) {
					cell.setCellValue((Calendar) value);
					cell.setCellStyle(getDateStyle(workbook));
				} else {
					cell.setCellValue(value.toString());
				}
			}
		}

		// auto size columns
		for (int c = 0; c < MAX_NUMBER_OF_COLUMNS; c++) {
			sheet.autoSizeColumn(c);
		}

	}

	private CellStyle getPropertyNameStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTableSection(Workbook workbook, Report report, TableSection tableSection) {
		Sheet sheet = createSheet(report, tableSection, workbook);

		DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();

		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		List<PropertyInfo> propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(tableSection.getIntrospectedObjectType(), propertyInfoFilter, propertyInfoComparator);

		// hide remaining columns
		for (int c = propertyInfos.size(); c < 256; c++) {
			sheet.setColumnHidden(c, true);
		}

		// // header title
		int rowNr = 0;
		Row row = sheet.createRow(rowNr++);
		// row.setHeightInPoints(25);
		// Cell titleCell = row.createCell(0);
		// titleCell.setCellValue(title);
		// titleCell.setCellStyle(HEADER_TITLE_STYLE);
		// sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, propertyIds.length));

		// header columns
		row = sheet.createRow(rowNr++);
		int columnNr = 0;
		Cell cell;
		for (PropertyInfo propertyInfo : propertyInfos) {
			cell = row.createCell(columnNr++);
			String columnHeaderText = propertyInfo.getText();
			cell.setCellValue(columnHeaderText);
			cell.setCellStyle(getColumnHeaderStyle(workbook));
		}

		// auto filter
		sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, propertyInfos.size()));

		// Freeze header rows
		sheet.createFreezePane(0, rowNr, 0, rowNr);

		// add domain objects as rows
		for (Object domainObject : tableSection.getIntrospectedObjects()) {
			row = sheet.createRow(rowNr++);
			columnNr = 0;
			for (PropertyInfo propertyInfo : propertyInfos) {
				cell = row.createCell(columnNr++);
				// put value in the cell with the right type
				Object value = propertyInfo.getValue(domainObject);
				Class<?> propertyType = null;
				if (value == null) {
					cell.setCellValue("");
				} else {
					propertyType = value.getClass();
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
						cell.setCellStyle(getDateStyle(workbook));
					} else if (Calendar.class.isAssignableFrom(propertyType)) {
						cell.setCellValue((Calendar) value);
						cell.setCellStyle(getDateStyle(workbook));
					} else {
						// if (columnNr == 1 && container instanceof Hierarchical) {
						// // Indent first column (depending on hierarchy level)
						// Hierarchical hierarchical = (Hierarchical) container;
						// String indent = "";
						// Object parent = hierarchical.getParent(domainObject);
						// while (parent != null) {
						// parent = hierarchical.getParent(parent);
						// indent += "   ";
						// }
						// cell.setCellValue(indent + value.toString());
						// } else {
						// cell.setCellValue(value.toString());
						// }
					}
				}
			}
		}

		// auto size columns
		for (int c = 0; c < propertyInfos.size(); c++) {
			sheet.autoSizeColumn(c);
		}

	}

	@Override
	public ByteArrayOutputStream createOutputStream(Workbook document, Report report) {
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			document.write(buffer);
			return buffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private Sheet createSheet(Report report, Section section, Workbook workbook) {
		Sheet sheet = workbook.createSheet(section.getSectionName());

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

		Header header = sheet.getHeader();
		header.setLeft(report.getReportName());
		header.setRight(section.getSectionName());

		// set footer
		Footer footer = sheet.getFooter();
		// add a fixed export date and time (not a dynamic HeaderFooter.date() )
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date EXPORT_DATE_TIME = new Date();
		footer.setLeft(dateFormat.format(EXPORT_DATE_TIME));
		footer.setRight("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());

		return sheet;
	}

	private CellStyle getDateStyle(Workbook wb) {
		if (dateStyle == null) {
			CreationHelper createHelper = wb.getCreationHelper();
			dateStyle = wb.createCellStyle();
			dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		}
		return dateStyle;
	}

	// private CellStyle createHeaderTitleStype(Workbook wb) {
	// CellStyle style = wb.createCellStyle();
	// style.setAlignment(CellStyle.ALIGN_CENTER);
	// style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	// style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	// Font font = wb.createFont();
	// font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	// font.setFontHeight((short) (font.getFontHeight() * 1.5));
	// style.setFont(font);
	// return style;
	// }

	private CellStyle getColumnHeaderStyle(Workbook wb) {
		if (columnHeaderStyle == null) {
			columnHeaderStyle = wb.createCellStyle();
			columnHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
			columnHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			columnHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Font font = wb.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			columnHeaderStyle.setFont(font);
			columnHeaderStyle.setBorderTop(CellStyle.BORDER_THIN);
			columnHeaderStyle.setBorderRight(CellStyle.BORDER_THIN);
			columnHeaderStyle.setBorderBottom(CellStyle.BORDER_THIN);
			columnHeaderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		}
		return columnHeaderStyle;
	}


}
