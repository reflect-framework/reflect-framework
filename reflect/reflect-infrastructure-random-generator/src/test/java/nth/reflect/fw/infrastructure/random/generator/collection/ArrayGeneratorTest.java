package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.ArrayGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.product.Product;
import nth.reflect.fw.infrastructure.random.generator.collection.product.ProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ArrayGeneratorTest {

	@Test
	public void testRandomProductArrayGeneratorWithSize() {
		int size = 10;
		ArrayGenerator<Product> randomProductArrayGenerator = new ArrayGenerator<>(Product.class, size, new ProductGenerator());
		Object o[] = randomProductArrayGenerator.generate();
		System.out.println(o[0]);
		Product[] array = (Product[]) randomProductArrayGenerator.generate();
		assertTrue(array.length == size);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomProductArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<Product> randomProductArrayGenerator = new ArrayGenerator<>( Product.class,min, max,
				new ProductGenerator());
		Product[] array = randomProductArrayGenerator.generate();
		assertTrue(array.length >= min);
		assertTrue(array.length <= max);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomIntArrayGeneratorWithSize() {
		int listSize = 10;
		ArrayGenerator<Integer> randomProductArrayGenerator = new ArrayGenerator<>(Integer.class, listSize,
				new IntGenerator(5, 10));
		Integer[] array = randomProductArrayGenerator.generate();
		assertTrue(array.length == listSize);
		assertArrayContainsValidIntegers(array);
	}

	@Test
	public void testRandomIntArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<Integer> randomProductArrayGenerator = new ArrayGenerator<>(Integer.class, min, max,
				new IntGenerator(5, 10));
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
