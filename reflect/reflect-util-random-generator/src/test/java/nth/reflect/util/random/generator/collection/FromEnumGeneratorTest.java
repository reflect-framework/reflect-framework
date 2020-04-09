package nth.reflect.util.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class FromEnumGeneratorTest {

	public enum TestEnum {
		ONE, TWO, THREE
	}

	@Test
	public void testEnumGenerator() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set<Enum> result = Random.fromEnum(TestEnum.class).generateSet(100);
		assertThat(result).hasSize(3);
		assertThat(result).containsOnlyOnce(TestEnum.ONE, TestEnum.TWO, TestEnum.THREE);
	}

}
