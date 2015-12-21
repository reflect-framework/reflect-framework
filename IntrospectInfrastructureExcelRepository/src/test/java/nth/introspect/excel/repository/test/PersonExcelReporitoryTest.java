package nth.introspect.excel.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.excel.repository.exception.ColumnNamesNotFoundException;
import nth.introspect.excel.repository.exception.CouldNotFindSheetsException;
import nth.introspect.generic.util.ClassList;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PersonExcelReporitoryTest {

	@Rule
	 public TemporaryFolder folder= new TemporaryFolder();
	
	private static final String TEST_TITLE = "Test title";
	private PersonExcelRepository personExcelRepository;
	private File excelFile;

	@Before
 	public void setUp() throws Exception {
 		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {
 
 			@Override
 			public List<Class<?>> getInfrastructureClasses() {
 				return new ClassList(PersonExcelRepository.class);
 			}
 		};
 		DependencyInjectionContainer container = application.createContainer();
 		personExcelRepository = container.get(PersonExcelRepository.class);
 		 excelFile = new File(PersonExcelRepository.class.getResource("/Persons.xlsx").toURI());
 	}

	
	@Test(expected = NullPointerException.class)
	public void readFromTableNoFileTest() throws Exception {
		File excelFile = null;
		personExcelRepository.readFromTable( new PersonExcelReader(excelFile));
	}

	@Test(expected = FileNotFoundException.class)
	public void readFromTableNoneExistingFileTest() throws Exception {
		File noneExistingFile = new File("DOES_NOT_EXIST.xlsx");
		personExcelRepository.readFromTable( new PersonExcelReader(noneExistingFile));
	}

	@Test(expected = InvalidOperationException.class)
	public void readFromTableNoneExcelFileTest() throws Exception {
		File noneExcelFile = new File(getClass().getResource("/NonExcelFile.txt").toURI());
		personExcelRepository.readFromTable(new PersonExcelReader(noneExcelFile));
	}

	@Test(expected=CouldNotFindSheetsException.class)
	public void readFromTableNonExistingSheetTest() throws Exception {
		personExcelRepository.readFromTable( new PersonExcelReader(excelFile, "NONE EXISITING SHEET NAME", PersonExcelReader.COLUMN_NAMES));
	}

	@Test (expected=ColumnNamesNotFoundException.class)
	public void readFromTableNonExistingColumnName() throws Exception {
		String[] columnNames=new String[] {PersonExcelReader.NAME, PersonExcelReader.NUMBER_OF_CHILDREN, PersonExcelReader.BIRTHDAY, PersonExcelReader.WEIGHT, "BOGUS_COLUMN"};
		personExcelRepository.readFromTable( new PersonExcelReader(excelFile, PersonExcelReader.PERSONS_SHEET, columnNames));
	}

	@Test
	public void readFromTableObjectsTest() throws Exception {
		List<Person> persons = personExcelRepository.readFromTable( new PersonExcelReader(excelFile));

		assertEquals(2, persons.size());

		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Person person1 = persons.get(0);
		assertEquals("Nils", person1.getName());
		assertEquals("1974-06-09", formatter.format(person1.getBirthday()));
		assertEquals(2, person1.getNumberOfChildren());
		assertEquals(90, person1.getWeight(),1e-15);

		Person person2 = persons.get(1);
		assertEquals("Tycho", person2.getName());
		assertEquals("2003-10-19", formatter.format(person2.getBirthday()));
		assertEquals(0, person2.getNumberOfChildren());
		assertEquals(25.4, person2.getWeight(),1e-15);

	}
	
	@Test
	public void writeAsFormTest() throws Exception {
		File excelFile = new File(folder.getRoot().getAbsoluteFile()+"/test1.xlsx");
		
		Person person1=new Person();
		person1.setName("Nils");
		Calendar calendar = Calendar.getInstance();
		calendar.set(1974, 6, 9);
		person1.setBirthday(calendar.getTime());
		person1.setWeight(90);
		person1.setNumberOfChildren(1);
		XSSFWorkbook workbook = personExcelRepository.createWorkbook();
		workbook = personExcelRepository.writeAsForm(workbook, TEST_TITLE, person1);
		personExcelRepository.writeExcelFile(excelFile, workbook);
		assertTrue(excelFile.exists());
	}
	
	@Test
	public void writeAsTableTest() throws Exception {
		File excelFile = new File(folder.getRoot().getAbsoluteFile()+"/test2.xlsx");
		
		Person nils=new Person();
		nils.setName("Nils");
		Calendar calendar = Calendar.getInstance();
		calendar.set(1974, 6, 9,0,0,0);
		nils.setBirthday(calendar.getTime());
		nils.setWeight(90);
		nils.setNumberOfChildren(1);
		
		Person tycho=new Person();
		tycho.setName("Tycho");
		calendar = Calendar.getInstance();
		calendar.set(2003, 10, 19,0,0,0);
		tycho.setBirthday(calendar.getTime());
		tycho.setWeight(35);
		tycho.setNumberOfChildren(0);
		
		List<Person> persons=new ArrayList<>();
		persons.add(nils);
		persons.add(tycho);
		
		XSSFWorkbook workbook = personExcelRepository.createWorkbook();
		workbook = personExcelRepository.writeAsTable(workbook, PersonExcelReader.PERSONS_SHEET, Person.class, persons);
		personExcelRepository.writeExcelFile(excelFile, workbook);
		assertTrue(excelFile.exists());
		
		persons=personExcelRepository.readFromTable(new PersonExcelReader(excelFile));
		assertEquals(2, persons.size());
		
		Person person1 = persons.get(0);
		assertEquals(person1.getName(), nils.getName());
		assertEquals(person1.getBirthday(), nils.getBirthday());
		assertEquals(person1.getNumberOfChildren(), nils.getNumberOfChildren());
		assertEquals(person1.getWeight(), nils.getWeight(),1e-15);

		Person person2 = persons.get(1);
		assertEquals(person2.getName(), tycho.getName());
		assertEquals(person2.getBirthday(), tycho.getBirthday());
		assertEquals(person2.getNumberOfChildren(), tycho.getNumberOfChildren());
		assertEquals(person2.getWeight(), tycho.getWeight(),1e-15);

	}

}
