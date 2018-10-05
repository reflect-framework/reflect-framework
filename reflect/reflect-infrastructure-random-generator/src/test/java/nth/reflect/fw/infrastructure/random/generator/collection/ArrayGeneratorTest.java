package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.ArrayGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProduct;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ArrayGeneratorTest {

	@Test
	public void testRandomProductArrayGeneratorWithSize() {
		int size = 10;
		ArrayGenerator<TestProduct> randomProductArrayGenerator = new ArrayGenerator<>(TestProduct.class,
				new TestProductGenerator(), size);
		TestProduct[] array = (TestProduct[]) randomProductArrayGenerator.generate();
		assertTrue(array.length == size);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomProductArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<TestProduct> randomProductArrayGenerator = new ArrayGenerator<>(TestProduct.class,
				new TestProductGenerator(), min, max);
		TestProduct[] array = randomProductArrayGenerator.generate();
		assertTrue(array.length >= min);
		assertTrue(array.length <= max);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomIntArrayGeneratorWithSize() {
		int listSize = 10;
		ArrayGenerator<Integer> randomProductArrayGenerator = new ArrayGenerator<>(Integer.class,
				new IntGenerator(5, 10), listSize);
		Integer[] array = randomProductArrayGenerator.generate();
		assertTrue(array.length == listSize);
		assertArrayContainsValidIntegers(array);
	}

	@Test
	public void testRandomIntArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<Integer> randomProductArrayGenerator = new ArrayGenerator<>(Integer.class,
				new IntGenerator(5, 10), min, max);
		Integer[] array = randomProductArrayGenerator.generate();
		assertTrue(array.length >= min);
		assertTrue(array.length <= max);
		assertArrayContainsValidIntegers(array);
	}

	private void assertArrayContainsValidIntegers(Integer[] array) {
		for (Integer integer : array) {
			assertTrue(integer >= 5);
			assertTrue(integer <= 10);
		}
	}

	private void assertArrayContainsValidProduct(TestProduct[] array) {
		for (TestProduct testProduct : array) {
			assertTrue(testProduct.getCode().length() > 0);
			assertTrue(testProduct.getName().length() > 0);
			assertTrue(testProduct.getDetails().length() > 0);
			assertTrue(testProduct.getPrice().compareTo(BigDecimal.ZERO) == 1);
		}
	}

}
