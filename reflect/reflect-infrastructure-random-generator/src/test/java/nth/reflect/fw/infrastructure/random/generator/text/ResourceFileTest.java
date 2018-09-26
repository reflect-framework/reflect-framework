package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile.Row;


public class ResourceFileTest {

	@Rule
	public final ExpectedException exception=ExpectedException.none();
	
	private static final String RESOURCE_FILE_TEST_TXT = "ResourceFileTest.txt";
	private static final String BOGUS_FILE_NAME = "bogusFileName";

	@Test
	public void testResourceFileString() {
		ResourceFile resourceFile = new ResourceFile("");
		List<String> values = resourceFile.getValues();
		assertThat(values, hasSize(0));

		resourceFile = new ResourceFile(BOGUS_FILE_NAME);
		values = resourceFile.getValues();
		assertThat(values, hasSize(0));

		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT);
		values = resourceFile.getValues();
		assertThat(values, hasSize(3));
		assertThat(values, hasItems("1a,1b,1c", "2a,2b,2c", "3a,3b,3c"));
	}

	@Test
	public void testResourceFileStringInt() {
		ResourceFile resourceFile = new ResourceFile("", 2);
		List<String> values = resourceFile.getValues();
		assertThat(values, hasSize(0));

		resourceFile = new ResourceFile(BOGUS_FILE_NAME, 2);
		values = resourceFile.getValues();
		assertThat(values, hasSize(0));

		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT, 1);
		values = resourceFile.getValues();
		assertThat(values, hasSize(3));
		assertThat(values, hasItems("1b", "2b", "3b"));

		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT, 2);
		values = resourceFile.getValues();
		assertThat(values, hasSize(3));
		assertThat(values, hasItems("1c", "2c", "3c"));
		
		exception.expect(IndexOutOfBoundsException.class);
		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT, 3);
	}

	@Test
	public void testResourceFileStringPredicateOfQsuperRowInt() {
		Predicate<? super Row> filter = row->row.getCell(1).contains("2");

		ResourceFile resourceFile = new ResourceFile("", filter,2);
		List<String> values = resourceFile.getValues();
		assertThat(values, hasSize(0));

		resourceFile = new ResourceFile(BOGUS_FILE_NAME, filter,2);
		values = resourceFile.getValues();
		assertThat(values, hasSize(0));

		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT, filter, 1);
		values = resourceFile.getValues();
		assertThat(values, hasSize(1));
		assertThat(values, hasItems("2b"));

		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT, filter,2);
		values = resourceFile.getValues();
		assertThat(values, hasSize(1));
		assertThat(values, hasItems( "2c"));
		
		exception.expect(IndexOutOfBoundsException.class);
		resourceFile = new ResourceFile(RESOURCE_FILE_TEST_TXT, filter,3);
	}

}
