package nth.reflect.util.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.generator.collection.SetGenerator;
import nth.reflect.util.random.generator.number.BoolGenerator;
import nth.reflect.util.random.generator.number.IntGenerator;

public class SetGeneratorTest {

	@Test
	public void testForSize() {
		Set<Boolean> booleans = new SetGenerator<>(new BoolGenerator(), 0).generate();
		assertThat(booleans).hasSize(0);

		booleans = new SetGenerator<>(new BoolGenerator(), 1).generate();
		assertThat(booleans).hasSize(1);

		booleans = new SetGenerator<>(new BoolGenerator(), 10).generate();
		assertThat(booleans).hasSize(2);

		Set<Integer> integers = new SetGenerator<>(new IntGenerator(), 10).generate();
		assertThat(integers).hasSize(10);

		integers = new SetGenerator<>(new IntGenerator(0, 5), 1000).generate();
		assertThat(integers).hasSize(5);
	}

	@Test
	public void testForRange() {
		Set<Boolean> booleans = new SetGenerator<>(new BoolGenerator(), 0, 0).generate();
		assertThat(booleans).hasSize(0);

		booleans = new SetGenerator<>(new BoolGenerator(), 1, 1).generate();
		assertThat(booleans).hasSize(1);

		booleans = new SetGenerator<>(new BoolGenerator(), 5, 100).generate();
		assertThat(booleans).hasSize(2);

		Set<Integer> integers = new SetGenerator<>(new IntGenerator(0, 2), 5000, 10000).generate();
		assertThat(booleans).hasSize(2);

		int min = 5;
		int max = 10;
		for (int i = 0; i < 20; i++) {
			integers = new SetGenerator<>(new IntGenerator(), min, max).generate();
			assertThat(integers).hasSizeGreaterThanOrEqualTo(min).hasSizeLessThanOrEqualTo(max);
		}

		min = 10;
		max = 5;
		for (int i = 0; i < 20; i++) {
			integers = new SetGenerator<>(new IntGenerator(), min, max).generate();
			assertThat(integers).hasSizeGreaterThanOrEqualTo(min).hasSizeLessThanOrEqualTo(min);
		}

	}

}
