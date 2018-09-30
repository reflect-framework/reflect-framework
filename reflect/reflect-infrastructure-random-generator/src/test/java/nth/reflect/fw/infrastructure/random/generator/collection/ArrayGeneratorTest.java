package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.Product;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.ProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ArrayGeneratorTest {

	@Test
	public void testRandomProductArrayGeneratorWithSize() {
		int size = 10;
		ArrayGenerator<Product> randomProductArrayGenerator = new ArrayGenerator<>(Product.class,
				new ProductGenerator(), size);
		Product[] array = (Product[]) randomProductArrayGenerator.generate();
		assertTrue(array.length == size);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomProductArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<Product> randomProductArrayGenerator = new ArrayGenerator<>(Product.class,
				new ProductGenerator(), min, max);
		Product[] array = randomProductArrayGenerator.generate();
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

	private void assertArrayContainsValidProduct(Product[] array) {
		for (Product product : array) {
			assertTrue(product.getCode().length() > 0);
			assertTrue(product.getName().length() > 0);
			assertTrue(product.getDetails().length() > 0);
			assertTrue(product.getPrice().compareTo(BigDecimal.ZERO) == 1);
		}
	}

}
