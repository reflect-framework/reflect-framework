package nth.reflect.infra.excel.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.stream.DownloadStream;
import nth.reflect.infra.excel.repository.reader.ExcelReader;
import nth.reflect.infra.excel.repository.writer.ExcelFormWriter;
import nth.reflect.infra.excel.repository.writer.ExcelTableWriter;

/**
 * TODO implement Marshaller, Unmarshaller, Parser and Printer, see
 * reflect-infrastructure-converter
 * 
 * {@link InfrastructureObject} to access Microsoft Excel files:
 * <ul>
 * <li>{@link #readAll(ExcelReader)} to read object from Excel sheets</li>
 * <li>{@link #writeAsForm(Object)} to write an object as Excel sheet as a form
 * (properties in rows)</li>
 * <li>{@link #writeAsTable(Object)} to write objects as an Excel sheet as a
 * table (properties in columns)</li>
 * </ul>
 * 
 * @author nilsth
 *
 * @param <T>
 */
public abstract class ExcelRepository<T> {

	private final ReflectionProvider reflectionProvider;

	public ExcelRepository(ReflectionProvider reflectionProvider) {
		this.reflectionProvider = reflectionProvider;
	}

	public List<T> readFromTable(ExcelReader<T> excelReader) throws Exception {
		return excelReader.readAll();
	}

	public XSSFWorkbook writeAsForm(XSSFWorkbook workbook, String title, T object) throws IOException {
		return new ExcelFormWriter().write(workbook, title, reflectionProvider, object);
	}

	public XSSFWorkbook writeAsTable(XSSFWorkbook workbook, String title, Class<?> objectType, Collection<T> objects)
			throws IOException {
		return new ExcelTableWriter().write(workbook, title, reflectionProvider, objectType, objects);
	}

	public XSSFWorkbook readOrCreateExcelFile(File excelFile) throws IOException {
		if (excelFile.exists()) {
			return readExcelFile(excelFile);
		}
		return createWorkbook();
	}

	public XSSFWorkbook createWorkbook() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		return workbook;
	}

	public void writeExcelFile(File excelFile, XSSFWorkbook workbook) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(excelFile);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public XSSFWorkbook readExcelFile(File excelFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(excelFile);
		XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
		return workBook;
	}

	public DownloadStream createDownloadStream(XSSFWorkbook workbook, String title) {
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			workbook.write(buffer);
			InputStream inputStream = new ByteArrayInputStream(buffer.toByteArray());
			String filePath = getFilePath(title);
			File file = new File(filePath);
			DownloadStream downloadStream = new DownloadStream(file, inputStream);
			return downloadStream;
		} catch (Exception e) {
			throw new CreatingExcelReportException(e);
		}
	}

	private String getFilePath(String title) {
		SimpleDateFormat format = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
		StringBuilder filePath = new StringBuilder();
		filePath.append(title);
		filePath.append(format.format(new Date()));
		filePath.append(".xlsx");
		return filePath.toString();
	}

}
