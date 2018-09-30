package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class StringGeneratorTest {

	private static final String CCC = "ccC";
	private static final String BBB = "bbB";
	private static final String AAA = "aaA";
	private static final String[] VALUES_ARRAY = new String[] { AAA, BBB, CCC };
	private static final String[] VALUES_ARRAY_LOWER_CASE = new String[] { AAA.toLowerCase(), BBB.toLowerCase(),
			CCC.toLowerCase() };
	private static final String[] VALUES_ARRAY_UPPER_CASE = new String[] { AAA.toUpperCase(), BBB.toUpperCase(),
			CCC.toUpperCase() };
	private static final String[] VALUES_ARRAY_CAPITAL_CASE = new String[] { FromStringListGenerator.capitalize(AAA), FromStringListGenerator.capitalize(BBB),
			FromStringListGenerator.capitalize(CCC) };

	@Test
	public void testForLowerCase() {
		Set<String> result = Random.fromStringListGenerator().forValues(Arrays.asList(VALUES_ARRAY)).forLowerCase().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY_LOWER_CASE));
	}

	@Test
	public void testForUpperCase() {
		Set<String> result = Random.fromStringListGenerator().forValues(Arrays.asList(VALUES_ARRAY)).forUpperCase().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY_UPPER_CASE));
	}

	
	@Test
	public void testForCapitalCase() {
		Set<String> result = Random.fromStringListGenerator().forValues(Arrays.asList(VALUES_ARRAY)).forFirstCharCapitalCase().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY_CAPITAL_CASE));
	}


}
