package nth.introspect.report.pdf;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfReportProvider extends ReportProvider<Document> {

	private static final float HEADER_HEIGHT = 10;
	private static final float FOOTER_HEIGHT = 20;
//	private static Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font SMALL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	private static Font SMALL_FONT_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private ByteArrayOutputStream buffer;

	@Override
	public Document createDocument(Report report) {
		Document document = new Document();
		buffer = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, buffer);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date EXPORT_DATE_TIME = new Date();
			HeaderFooter event = new HeaderFooter(report.getReportName(), dateFormat.format(EXPORT_DATE_TIME));
			writer.setPageEvent(event);

			document.open();
			// Increase footer margin
			document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + HEADER_HEIGHT, document.bottomMargin() + FOOTER_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

	@Override
	public void addFormSection(Document document, Report report, FormSection formSection) {
		int MAX_NUMBER_OF_COLUMNS = 2;
		
		addInvisibleChapterForHeader(document, formSection);
		
		// create table
		PdfPTable pdfTable = new PdfPTable(MAX_NUMBER_OF_COLUMNS);
		pdfTable.setHeaderRows(2);
		pdfTable.setWidthPercentage(100);

		// // header title
		// PdfPCell cell = new PdfPCell(new Paragraph(title, TITLE_FONT));
		// cell.setColspan(MAX_NUMBER_OF_COLUMNS);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBackgroundColor(new BaseColor(Color.LIGHT_GRAY));
		// cell.setPadding(5.0f);
		// pdfTable.addCell(cell);

		// add properties as rows
		Object domainObject = formSection.getIntrospectedObject();
		Class<?> domainClass = domainObject.getClass();
		DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
		
		// get propertyInfos
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		List<PropertyInfo> propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(domainClass, propertyInfoFilter, propertyInfoComparator);

		for (PropertyInfo propertyInfo : propertyInfos) {
			// add propertyName
			String propertyName = propertyInfo.getText();
			PdfPCell cell = new PdfPCell(new Paragraph(propertyName, SMALL_FONT));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfTable.addCell(cell);

			// put value in the cell with the right type
			Object value = propertyInfo.getValue(domainObject);
			if (value == null) {
				pdfTable.addCell("");
			} else {
				value = propertyInfo.getFormat().format(value);
				pdfTable.addCell(new Phrase(value.toString(), SMALL_FONT));
			}

		}

		// add table to PDF document
		try {
			document.add(pdfTable);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addTableSection(Document document, Report report, TableSection tableSection) {
		addInvisibleChapterForHeader(document, tableSection);
		
		Class<?> introspectedClass = tableSection.getIntrospectedObjectType();

		//get propertyInfos
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		List<PropertyInfo> propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(introspectedClass, propertyInfoFilter, propertyInfoComparator);
				
		// create table
		PdfPTable pdfTable = new PdfPTable(propertyInfos.size());
		pdfTable.setHeaderRows(2);
		pdfTable.setWidthPercentage(100);

		// header title
		// PdfPCell cell = new PdfPCell(new Paragraph(title, TITLE_FONT));
		// cell.setColspan(columnInfos.size());
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBackgroundColor(new BaseColor(Color.LIGHT_GRAY));
		// cell.setPadding(5.0f);
		// pdfTable.addCell(cell);

		// header columns
		for (PropertyInfo propertyInfo : propertyInfos) {
			String columnHeaderText = propertyInfo.getText();
			PdfPCell cell = new PdfPCell(new Paragraph(columnHeaderText, SMALL_FONT_BOLD));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(new BaseColor(Color.LIGHT_GRAY));
			// cell.setPadding(10.0f);
			pdfTable.addCell(cell);
		}

		// add domain objects as rows
		for (Object domainObject : tableSection.getIntrospectedObjects()) {
			for (PropertyInfo propertyInfo : propertyInfos) {
				// put value in the cell with the right type
				Object value = propertyInfo.getValue(domainObject);
				if (value == null) {
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
					// pdfTable.addCell(new Phrase(indent + value.toString(), SMALL_FONT));
					// } else {
					pdfTable.addCell(new Phrase(value.toString(), SMALL_FONT));
					// }

				}
			}
		}

		// add table to PDF document
		try {
			document.add(pdfTable);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}

	}

	public void addInvisibleChapterForHeader(Document document, Section section) {
		try {
			Paragraph paragraph=new Paragraph(section.getSectionName(), FontFactory.getFont(FontFactory.COURIER,1f,BaseColor.WHITE));//hide chapter
			Chapter chapter = new Chapter(paragraph,0);
			document.add(chapter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ByteArrayOutputStream createOutputStream(Document document, Report report) {

		document.close();
		return buffer;
	}

	/** Inner class to add a header and a footer. */
	class HeaderFooter extends PdfPageEventHelper {
		/** Current page number (will be reset for every chapter). */
		int pagenumber;
		private Phrase exportDate;
		private String paragraphName;
		private final String reportName;

		public HeaderFooter(String reportName, String exportDate) {
			this.reportName = reportName;
			this.exportDate = new Phrase(exportDate);
		}

		/**
		 * Initialize one of the headers.
		 * 
		 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
		 */
		public void onOpenDocument(PdfWriter writer, Document document) {
		}

		/**
		 * Initialize one of the headers, based on the chapter title; reset the page number.
		 * 
		 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onChapter(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document, float, com.itextpdf.text.Paragraph)
		 */
		public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
			paragraphName = title.getContent().substring(title.getContent().indexOf(".")+2);
		}

		/**
		 * Increase the page number and adds the header.
		 * 
		 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
		 */
		public void onStartPage(PdfWriter writer, Document document) {
			pagenumber++;
		}

		/**
		 * Adds the header and the footer.
		 * 
		 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
		 */
		public void onEndPage(PdfWriter writer, Document document) {
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(reportName), document.left(),  document.top()+HEADER_HEIGHT, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(paragraphName), document.right(), document.top()+HEADER_HEIGHT, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(exportDate), document.left(), document.bottomMargin() - FOOTER_HEIGHT, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(String.format("Page %d", pagenumber)), document.right(), document.bottomMargin() - FOOTER_HEIGHT, 0);
		}
	}

}
