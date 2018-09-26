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
	private static final String CCC = "ccC";
	private static final String BBB = "bbB";
	private static final String AAA = "aaA";
	private static final String NEW_LINE = "\n";
	private static final String VALUES_STRING = AAA + NEW_LINE + BBB + NEW_LINE + CCC;
	private static final String[] VALUES_ARRAY = new String[] { AAA, BBB, CCC };
	private static final String[] VALUES_ARRAY_LOWER_CASE = new String[] { AAA.toLowerCase(), BBB.toLowerCase(),
			CCC.toLowerCase() };
	private static final String[] VALUES_ARRAY_UPPER_CASE = new String[] { AAA.toUpperCase(), BBB.toUpperCase(),
			CCC.toUpperCase() };
	private static final String[] VALUES_ARRAY_CAPITAL_CASE = new String[] { StringGenerator.capitalize(AAA), StringGenerator.capitalize(BBB),
			StringGenerator.capitalize(CCC) };
	private static final String BOGUS_FILE_NAME = "bogusFileName.txt";

	@Test
	public void testForNoParameters() {
		String string = Random.stringGenerator().generate();
		assertEquals(EMPTY_STRING, string);

		string = Random.stringGenerator().generateString(10);
		assertEquals(EMPTY_STRING, string);

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
		assertThat(result, hasItems(VALUES_ARRAY));
	}

	@Test
	public void testForValuesString() {
		Set<String> result = Random.stringGenerator().forValues(VALUES_STRING).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY));
	}

	@Test
	public void testForValuesListOfString() {
		Set<String> result = Random.stringGenerator().forValues(Arrays.asList(VALUES_ARRAY)).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY));
	}

	@Test
	public void testForValuesInputStream() {
		InputStream inputStream = new ByteArrayInputStream(VALUES_STRING.getBytes(StandardCharsets.UTF_8));
		Set<String> result = Random.stringGenerator().forValues(inputStream).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY));
	}


	@Test
	public void testForLowerCase() {
		Set<String> result = Random.stringGenerator().forValues(Arrays.asList(VALUES_ARRAY)).forLowerCase().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY_LOWER_CASE));
	}

	@Test
	public void testForUpperCase() {
		Set<String> result = Random.stringGenerator().forValues(Arrays.asList(VALUES_ARRAY)).forUpperCase().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY_UPPER_CASE));
	}

	
	@Test
	public void testForCapitalCase() {
		Set<String> result = Random.stringGenerator().forValues(Arrays.asList(VALUES_ARRAY)).forFirstCharCapitalCase().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY_CAPITAL_CASE));
	}

}
