package nth.reflect.util.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.generator.collection.FromListGenerator;

public class FromListGeneratorTest {

	private static final String CCC = "ccC";
	private static final String BBB = "bbB";
	private static final String AAA = "aaA";
	private static final List<String> VALUES_ARRAY = Arrays.asList(AAA, BBB, CCC);

	@Test
	public void testForValuesListOfString() {
		Set<String> result = new FromListGenerator<String>(VALUES_ARRAY).generateSet(100);
		assertThat(result).hasSize(3);
		assertThat(result).containsOnlyElementsOf(VALUES_ARRAY);
	}

}
