package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class FromListGeneratorTest {

	private static final String CCC = "ccC";
	private static final String BBB = "bbB";
	private static final String AAA = "aaA";
	private static final String[] VALUES_ARRAY = new String[] { AAA, BBB, CCC };
	
	@Test
	public void testForValuesListOfString() {
		Set<String> result = new FromListGenerator<String>(Arrays.asList(VALUES_ARRAY)).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(VALUES_ARRAY));
	}


	
}
