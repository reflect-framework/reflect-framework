package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class StringGeneratorTest {

	private static final String THREE = "3";
	private static final String TWO = "2";
	private static final String ONE = "1";
	private static final String NEW_LINE = "\n";
	private static final String POOL_STRING=ONE+NEW_LINE+ TWO+NEW_LINE+ THREE;
	private static final String[] POOL_ARRAY=new String[] {ONE, TWO, THREE};
	private static final List<String> POOL_LIST=Arrays.asList(POOL_ARRAY);
	
	
	@Test
	public void testForPoolFromGeneratorResourceFile() {
		Set<String> result = Random.stringGenerator().forPoolFromGeneratorResourceFile().generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));		
	}

	@Test
	public void testForPoolString() {
		Set<String> result = Random.stringGenerator().forPool(POOL_STRING).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));
	}

	@Test
	public void testForPoolListOfString() {
		Set<String> result = Random.stringGenerator().forPool(POOL_LIST).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));
	}

	@Test
	public void testForPoolInputStream() {
		InputStream inputStream = new ByteArrayInputStream(POOL_STRING.getBytes(StandardCharsets.UTF_8));
		Set<String> result = Random.stringGenerator().forPool(inputStream).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(POOL_ARRAY));		
	}

}
