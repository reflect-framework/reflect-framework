package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class FromEnumGeneratorTest {

	public enum TestEnum {
		ONE, TWO, THREE
	}
	
	@Test
	public void testEnumGenerator() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set<Enum> result = Random.fromEnumGenerator(TestEnum.class).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItems(TestEnum.values()));
	}

}
