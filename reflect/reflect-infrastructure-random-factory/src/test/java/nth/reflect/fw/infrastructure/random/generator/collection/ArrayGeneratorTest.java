package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import nth.reflect.fw.infrastructure.randomfactory.product.Product;
import nth.reflect.fw.infrastructure.randomfactory.product.RandomProductFactory;

public class RandomArrayFactoryTest {

	@Test
	public void testRandomProductArrayFactoryWithSize() {
		int size = 10;
		RandomArrayFactory<Product> randomProductArrayFactory = new RandomArrayFactory<>(Product.class, size, new RandomProductFactory());
		Object o[] = randomProductArrayFactory.create();
		System.out.println(o[0]);
		Product[] array = (Product[]) randomProductArrayFactory.create();
		assertTrue(array.length == size);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomProductArrayFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		RandomArrayFactory<Product> randomProductArrayFactory = new RandomArrayFactory<>( Product.class,min, max,
				new RandomProductFactory());
		Product[] array = randomProductArrayFactory.create();
		assertTrue(array.length >= min);
		assertTrue(array.length <= max);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomIntArrayFactoryWithSize() {
		int listSize = 10;
		RandomArrayFactory<Integer> randomProductArrayFactory = new RandomArrayFactory<>(Integer.class, listSize,
				new RandomIntFactory(5, 10));
		Integer[] array = randomProductArrayFactory.create();
		assertTrue(array.length == listSize);
		assertArrayContainsValidIntegers(array);
	}

	@Test
	public void testRandomIntArrayFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		RandomArrayFactory<Integer> randomProductArrayFactory = new RandomArrayFactory<>(Integer.class, min, max,
				new RandomIntFactory(5, 10));
		Integer[] array = randomProductArrayFactory.create();
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
