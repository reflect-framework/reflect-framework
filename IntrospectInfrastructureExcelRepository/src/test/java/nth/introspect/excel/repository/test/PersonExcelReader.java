package nth.introspect.excel.repository.test;

import java.io.File;
import java.util.Map;

import nth.introspect.excel.repository.reader.ExcelTableReader;
import nth.introspect.excel.repository.sheetfilter.SheetFilter;
import nth.introspect.excel.repository.sheetfilter.SheetNameFilter;

import org.apache.poi.ss.usermodel.Row;

public class PersonExcelReader extends ExcelTableReader<Person> {

	public static final String PERSONS_SHEET = "persons";
	public static final String NAME = "Name";
	public static final String NUMBER_OF_CHILDREN = "Number of children";
	public static final String BIRTHDAY = "Birthday";
	public static final String WEIGHT = "Weight";
	public static final String[] COLUMN_NAMES = new String[] { NAME,
			NUMBER_OF_CHILDREN, BIRTHDAY, WEIGHT };
	private final SheetFilter sheetFilter;
	private final String[] columnNames;
	private final File excelFile;

	public PersonExcelReader(File excelFile) {
		this.excelFile = excelFile;
		this.sheetFilter = new SheetNameFilter(PERSONS_SHEET);
		this.columnNames = COLUMN_NAMES;
	}
	

	public PersonExcelReader(File excelFile,String sheetName, String[] columnNames) {
		this.excelFile = excelFile;
		this.sheetFilter = new SheetNameFilter(sheetName);
		this.columnNames = columnNames;
	}

	@Override
	public Person readRow(Row row, Map<String, Integer> columnIndexes) {
		Person person = new Person();
		person.setName(getString(row, columnIndexes.get(NAME)));
		person.setNumberOfChildren(getInteger(row,
				columnIndexes.get(NUMBER_OF_CHILDREN)));
		person.setBirthday(getDate(row, columnIndexes.get(BIRTHDAY)));
		person.setWeight(getDouble(row, columnIndexes.get(WEIGHT)));
		return person;
	}

	@Override
	public String[] getColumnNames() {
		return columnNames;
	}

	@Override
	protected File getExcelFile() throws Exception {
		return excelFile;
	}

	@Override
	public SheetFilter getSheetFilter() {
		return sheetFilter;
	}

}
