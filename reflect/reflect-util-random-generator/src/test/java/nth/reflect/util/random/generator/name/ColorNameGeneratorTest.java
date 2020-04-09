package nth.reflect.util.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class ColorNameGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.colorName().generateSet(100);
		assertThat(result).size().isGreaterThan(15);
		assertThat(result).contains("Red");
		assertThat(result).contains("Green");
		assertThat(result).contains("Yellow");
		assertThat(result).contains("Blue");
		assertThat(result).contains("White");
		assertThat(result).contains("Gray");
	}

}
