package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.SetGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class SetGeneratorTest {

	@Test
	public void testForSize() {
		int size = 0;
		Set<Boolean> booleans = new SetGenerator<>(new BoolGenerator(), size).generate();
		assertEquals(size, booleans.size());

		size = 1;
		booleans = new SetGenerator<>(new BoolGenerator(), size).generate();
		assertEquals(size, booleans.size());

		size = 10;
		booleans = new SetGenerator<>(new BoolGenerator(), size).generate();
		assertEquals(2, booleans.size());

		Set<Integer> integers = new SetGenerator<>(new IntGenerator(), size).generate();
		assertEquals(size, integers.size());

		size=1000;
		integers = new SetGenerator<>(new IntGenerator(0, 5), size).generate();
		assertEquals(5, integers.size());

	}

	@Test
	public void testForRange() {
		int min = 0;
		int max = 0;
		Set<Boolean> booleans = new SetGenerator<>(new BoolGenerator(), min, max).generate();
		assertEquals(max, booleans.size());

		min = 1;
		max = 1;
		booleans = new SetGenerator<>(new BoolGenerator(), min, max).generate();
		assertEquals(max, booleans.size());

		min = 5;
		max = 10;
		booleans = new SetGenerator<>(new BoolGenerator(), min, max).generate();
		assertEquals(2, booleans.size());

		min = 50000;
		max = 100000;
		Set<Integer> integers = new SetGenerator<>(new IntGenerator(0,2), min, max).generate();
		assertEquals(2, integers.size() );

		min = 5;
		max = 10;
		for (int i = 0; i < 20; i++) {
			integers = new SetGenerator<>(new IntGenerator(), min, max).generate();
			assertTrue(integers.size() >= min);
			assertTrue(integers.size() <= max);
		}
		
		min = 10;
		max = 5;
		for (int i = 0; i < 20; i++) {
			integers = new SetGenerator<>(new IntGenerator(), min, max).generate();
			assertTrue(integers.size() >= max);
			assertTrue(integers.size() <= min);
		}
		
	}

}
