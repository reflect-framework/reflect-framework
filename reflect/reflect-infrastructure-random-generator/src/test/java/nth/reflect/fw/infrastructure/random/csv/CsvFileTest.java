package nth.reflect.fw.infrastructure.random.csv;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CsvFileTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final String RESOURCE_FILE_TEST_TXT = "CsvFileTest.csv";
	private static final String BOGUS_FILE_NAME = "bogusFileName";

	@Test(expected = RuntimeException.class)
	public void testResourceFileStringEmptyFileName() {
		CsvFile csvFile = new CsvFile("");
		 List<CsvRow> rows = csvFile.getRows();
		assertThat(rows, hasSize(0));
	}

	@Test(expected = RuntimeException.class)
	public void testResourceFileStringBogusFileName() {
		CsvFile csvFile = new CsvFile(BOGUS_FILE_NAME);
		 List<CsvRow> rows = csvFile.getRows();
		assertThat(rows, hasSize(0));
	}

	@Test
	public void testResourceFileString() {
		CsvFile csvFile = new CsvFile(RESOURCE_FILE_TEST_TXT);
		List<CsvRow> rows = csvFile.getRows();
		assertThat(rows, hasSize(3));
		assertEquals(rows.get(0).getCell(0),"1a") ;
		assertEquals(rows.get(0).getCell(1),"1b") ;
		assertEquals(rows.get(0).getCell(2),"1c") ;
		assertEquals(rows.get(1).getCell(0),"2a") ;
		assertEquals(rows.get(1).getCell(1),"2b") ;
		assertEquals(rows.get(1).getCell(2),"2c") ;
		assertEquals(rows.get(2).getCell(0),"3a") ;
		assertEquals(rows.get(2).getCell(1),"3b") ;
		assertEquals(rows.get(2).getCell(2),"3c") ;

	}

}
