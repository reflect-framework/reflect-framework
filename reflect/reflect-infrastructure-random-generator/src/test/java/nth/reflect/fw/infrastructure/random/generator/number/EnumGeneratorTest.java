package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class EnumGeneratorTest {

	public enum TestEnum {
		ONE, TWO, THREE
	}
	
	@Test
	public void testEnumGenerator() {
		@SuppressWarnings("rawtypes")
		Set<Enum> result = Random.enumGenerator(TestEnum.class).generateSet(100);
		assertThat(result, hasSize(3));
		assertThat(result, hasItem(TestEnum.ONE));
		assertThat(result, hasItem(TestEnum.TWO));
		assertThat(result, hasItem(TestEnum.THREE));
	}

}
