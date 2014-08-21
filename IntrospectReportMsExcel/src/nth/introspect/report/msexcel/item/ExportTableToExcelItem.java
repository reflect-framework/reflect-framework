package nth.introspect.report.msexcel.item;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.property.TableOrderComparator;
import nth.introspect.provider.domain.info.property.TableVisibleFilter;
import nth.introspect.provider.userinterface.DownloadStream;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.util.TypeUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

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

public class ExportTableToExcelItem extends Item {

	private final ReadOnlyValueModel valueModel;
	private final String reportTitle;

	public ExportTableToExcelItem(ReadOnlyValueModel valueModel, String itemText, String reportTitle) {
		this.valueModel = valueModel;
		this.reportTitle = reportTitle;
		setText(itemText);
		setDescription(itemText);
		setIconURI(Introspect.getPathProvider().getImagePath("exportToExcel"));
		setAction(createAction(valueModel));
	}

	@Override
	public boolean isEnabled() {
		return valueModel.canGetValue();
	}

	private Action createAction(ReadOnlyValueModel valueModel) {
		return new Action() {
			@Override
			public void run() {
				exportToExcel();
			}
		};
	}

	public void exportToExcel() {
		DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
		Class<?> valueType = valueModel.getValueType();

		//get propertyInfos
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		List<PropertyInfo> propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(valueType, propertyInfoFilter, propertyInfoComparator);

		// create workbook and sheet
		Workbook wb;
		wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(reportTitle);

		// create styles
		CellStyle HEADER_TITLE_STYLE = createHeaderTitleStype(wb);
		CellStyle HEADER_COLUMNS_STYLE = createHeaderColumnsStype(wb);
		CellStyle DATE_STYLE = createDateStyle(wb);

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
		// repeat header when printing
		wb.setRepeatingRowsAndColumns(0, 0, 0, 0, 1);

		// set footer
		Footer footer = sheet.getFooter();
		// add a fixed export date and time (not a dynamic HeaderFooter.date() )
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date EXPORT_DATE_TIME = new Date();
		footer.setLeft(dateFormat.format(EXPORT_DATE_TIME));
		footer.setRight("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());

		// hide remaining columns
		for (int c = propertyInfos.size(); c < 256; c++) {
			sheet.setColumnHidden(c, true);
		}

		// header title
		int rowNr = 0;
		Row row = sheet.createRow(rowNr++);
		row.setHeightInPoints(25);
		Cell titleCell = row.createCell(0);
		titleCell.setCellValue(reportTitle);
		titleCell.setCellStyle(HEADER_TITLE_STYLE);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, propertyInfos.size()));

		// header columns
		row = sheet.createRow(rowNr++);
		int columnNr = 0;
		Cell cell;
		for (PropertyInfo propertyInfo : propertyInfos) {
			cell = row.createCell(columnNr++);
			String columnHeaderText = propertyInfo.getText();
			cell.setCellValue(columnHeaderText);
			cell.setCellStyle(HEADER_COLUMNS_STYLE);
		}

		// auto filter
		sheet.setAutoFilter(new CellRangeAddress(1, 1, 0, propertyInfos.size()));

		// Freeze header rows
		sheet.createFreezePane(0, rowNr, 0, rowNr);

		// get domain objects
		Collection<?> domainObjects = null;
		Object value = valueModel.getValue();
		if (value instanceof Collection) {
			// get selected domain objects
			domainObjects = (Collection<?>) value;
		} else {
			// selected only one row, so create a collection with only one domain object
			ArrayList<Object> collection = new ArrayList<Object>();
			collection.add(value);
			domainObjects = collection;
		}

		// add domain objects as rows
		for (Object domainObject : domainObjects) {
			row = sheet.createRow(rowNr++);
			columnNr = 0;
			for (PropertyInfo propertyInfo : propertyInfos) {
				cell = row.createCell(columnNr++);
				// put value in the cell with the right type
				Object cellValue = propertyInfo.getValue(domainObject);
				Class<?> cellType = propertyInfo.getPropertyType().getType();

				if (cellValue == null) {
					cell.setCellValue("");
				} else {
					if (TypeUtil.isDomainType(cellType) || TypeUtil.isEnum(cellType)) {
						String formatedValue = propertyInfo.getFormatedValue(domainObject);
						cell.setCellValue(formatedValue);
					} else if (String.class.isAssignableFrom(cellType)) {
						cell.setCellValue(((String) cellValue));
					} else if (Integer.class.isAssignableFrom(cellType)) {
						cell.setCellValue(((Integer) cellValue).doubleValue());
					} else if (Double.class.isAssignableFrom(cellType)) {
						cell.setCellValue((Double) cellValue);
					} else if (BigDecimal.class.isAssignableFrom(cellType)) {
						cell.setCellValue(((BigDecimal) cellValue).doubleValue());
					} else if (Boolean.class.isAssignableFrom(cellType)) {
						cell.setCellValue((Boolean) cellValue);
					} else if (Date.class.isAssignableFrom(cellType)) {
						cell.setCellValue((Date) cellValue);
						cell.setCellStyle(DATE_STYLE);
					} else if (Calendar.class.isAssignableFrom(cellType)) {
						cell.setCellValue((Calendar) cellValue);
						cell.setCellStyle(DATE_STYLE);
					} else {
						// TODO hierarchical
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

		// send stream
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			wb.write(buffer);
			SimpleDateFormat format = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
			String fileName = reportTitle + format.format(EXPORT_DATE_TIME) + ".xls";
			File file = new File(fileName);
			InputStream inputStream = new ByteArrayInputStream(buffer.toByteArray());
			DownloadStream downloadStream = new DownloadStream(file, inputStream);
			UserInterfaceProvider<?> userInterfaceProvider = Introspect.getUserInterfaceProvider();
			userInterfaceProvider.downloadFile(downloadStream);

		} catch (Exception e) {
			UserInterfaceProvider<?> userInterfaceProvider = Introspect.getUserInterfaceProvider();
			userInterfaceProvider.showErrorDialog(reportTitle, "Error creating excel report", e);
		}

	}

	private CellStyle createDateStyle(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		return cellStyle;
	}

	private CellStyle createHeaderTitleStype(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeight((short) (font.getFontHeight() * 1.5));
		style.setFont(font);
		return style;
	}

	private CellStyle createHeaderColumnsStype(Workbook wb) {
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

	// private String getSortingScript() {
	// StringBuffer vba = new StringBuffer();
	// vba.append("Private Sub Worksheet_SelectionChange(ByVal Target As Range)/n");
	// vba.append("	r = Target.Row/n");
	// vba.append("	If (r = 2) Then/n");
	// vba.append("	    columnNr = Target.Column/n");
	// vba.append("	    firstRowNr = 2/n");
	// vba.append("	    lastRowNr = Cells(Rows.Count, columnNr).End(xlUp).Row/n");
	// vba.append("	    If Not AutoFilterMode Then/n");
	// vba.append("	        Set rng = Target(Cells(1, columnNr), Target(lastRowNr, 1))/n");
	// vba.append("	    Else/n");
	// vba.append("	        Set rng = AutoFilter.Range/n");
	// vba.append("	    End If/n");
	// vba.append("	    Set rngF = Nothing/n");
	// vba.append("	    On Error Resume Next/n");
	// vba.append("	    With rng/n");
	// vba.append("	       'visible cells in first column of range/n");
	// vba.append("	       Set rngF = .Offset(1, 0).Resize(.Rows.Count - 1, 1) .SpecialCells(xlCellTypeVisible)/n");
	// vba.append("	    End With/n");
	// vba.append("	    On Error GoTo 0/n");
	// vba.append("	    If rngF Is Nothing Then/n");
	// vba.append("	         MsgBox \"No visible rows. Please try again.\"/n");
	// vba.append("	         Exit Sub/n");
	// vba.append("	    Else/n");
	// vba.append("	         firstRowNr = rngF(1).Row/n");
	// vba.append("	    End If/n");
	// vba.append("	    If Cells(firstRowNr, columnNr).Value < Cells(lastRowNr, columnNr).Value Then/n");
	// vba.append("	        mySortOrder = xlDescending/n");
	// vba.append("	    Else/n");
	// vba.append("	        mySortOrder = xlAscending/n");
	// vba.append("	    End If/n");
	// vba.append("	    Cells(firstRowNr, columnNr).Sort key1:=Cells(firstRowNr, columnNr), order1:=mySortOrder, header:=xlYes/n");
	// vba.append("	    'prevent Select event triggering again when we change the selection below/n");
	// vba.append("	    Application.EnableEvents = False/n");
	// vba.append("	    'move the cursor one row down so that the header can be re clicked (triggering a new selectionChanged event)/n");
	// vba.append("	    Target.Resize(2, 1).Select/n");
	// vba.append("	    Application.EnableEvents = True/n");
	// vba.append("	End If/n");
	// vba.append("End Sub/n");
	// return vba.toString();
	// }

}
