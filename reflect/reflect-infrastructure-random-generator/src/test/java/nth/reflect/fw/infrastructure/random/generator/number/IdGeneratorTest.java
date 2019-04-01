package nth.reflect.fw.infrastructure.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class IdGeneratorTest {

	@Test
	public void testGenerate() {
		IdGenerator idGenerator = Random.id();
		int integer1 = idGenerator.generate();
		int integer2 = idGenerator.generate();
		int integer3 = idGenerator.generate();
		assertThat(integer1).isEqualTo(0);
		assertThat(integer2).isEqualTo(1);
		assertThat(integer3).isEqualTo(2);
	}

	@Test
	public void testGenerateList() {
		int size = 8;
		List<Integer> ids = Random.id().generateList(size);
		assertThat(ids).hasSize(size);
		assertThat(ids).contains(0, 1, 2, 3, 4, 5, 6, 7);
	}

}
