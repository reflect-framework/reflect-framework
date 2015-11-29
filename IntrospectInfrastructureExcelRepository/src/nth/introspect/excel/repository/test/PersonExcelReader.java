package nth.introspect.excel.repository.test;

import java.util.Map;

import nth.introspect.excel.repository.reader.ExcelTableReader;
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

	public PersonExcelReader() {
		super(new SheetNameFilter(PERSONS_SHEET), COLUMN_NAMES);
	}

	public PersonExcelReader(String sheetName, String[] columnNames) {
		super(new SheetNameFilter(sheetName), columnNames);
	}

	@Override
	public Person readRow(Row row, Map<String, Integer> columnIndexes) {
		Person person = new Person();
		person.setName(getStringValue(row, columnIndexes.get(NAME)));
		person.setNumberOfChildren(getInteger(row,
				columnIndexes.get(NUMBER_OF_CHILDREN)));
		person.setBirthday(getDate(row, columnIndexes.get(BIRTHDAY)));
		person.setWeight(getDouble(row, columnIndexes.get(WEIGHT)));
		return person;
	}

}
