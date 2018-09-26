package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class StringGeneratorTest {

	private static final String EMPTY_STRING = "";
	private static final String THREE = "3";
	private static final String TWO = "2";
	private static final String ONE = "1";
	private static final String NEW_LINE = "\n";
	private static final String POOL_STRING=ONE+NEW_LINE+ TWO+NEW_LINE+ THREE;
	private static final String[] POOL_ARRAY=new String[] {ONE, TWO, THREE};
	private static final List<String> POOL_LIST=Arrays.asList(POOL_ARRAY);
	private static final String BOGUS_FILE_NAME = "bogusFileName.txt";
	
	

	@Test
	public void testForNoParameters() {
		String string = Random.stringGenerator().generate();
		assertEquals(EMPTY_STRING,string);
		
		string = Random.stringGenerator().generateString(10);
		assertEquals(EMPTY_STRING,string);
		
		List<String> list = Random.stringGenerator().generateList(20);
		assertThat(list, hasSize(20));
		assertThat(list, hasItems(EMPTY_STRING));
		
		Set<String> set = Random.stringGenerator().generateSet(20);
		assertThat(set, hasSize(1));
		assertThat(list, hasItems(EMPTY_STRING));
	}
	
	@Test
	public void testForValuesResourceFile() {
		Set<String> result = Random.stringGenerator().forValues(new ResourceFile(BOGUS_FILE_NAME)).generateSet(100);
		assertThat(result, hasSize(1));
		assertThat(result, hasItems(EMPTY_STRING));
		
		result = Random.stringGenerator().forValues(new ResourceFile("StringGeneratorTest.txt")).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));	
	}

	@Test
	public void testForValuesString() {
		Set<String> result = Random.stringGenerator().forValues(POOL_STRING).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));
	}

	@Test
	public void testForValuesListOfString() {
		Set<String> result = Random.stringGenerator().forValues(POOL_LIST).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));
	}

	@Test
	public void testForValuesInputStream() {
		InputStream inputStream = new ByteArrayInputStream(POOL_STRING.getBytes(StandardCharsets.UTF_8));
		Set<String> result = Random.stringGenerator().forValues(inputStream).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));		
	}

}
