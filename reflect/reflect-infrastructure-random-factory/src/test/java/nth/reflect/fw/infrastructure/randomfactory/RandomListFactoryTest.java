package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.randomfactory.product.Product;
import nth.reflect.fw.infrastructure.randomfactory.product.RandomProductFactory;

public class RandomListFactoryTest {

	@Test
	public void testRandomProductListFactoryWithSize() {
		int size = 10;
		RandomListFactory<Product> randomProductListFactory = new RandomListFactory<>(size, new RandomProductFactory());
		List<Product> list = randomProductListFactory.create();
		assertTrue(list.size() == size);
		assertListContainsValidProducts(list);
	}

	@Test
	public void testRandomProductListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		RandomListFactory<Product> randomProductListFactory = new RandomListFactory<>(min, max,
				new RandomProductFactory());
		List<Product> list = randomProductListFactory.create();
		assertTrue(list.size() >= min);
		assertTrue(list.size() <= max);
		assertListContainsValidProducts(list);
	}

	@Test
	public void testRandomIntListFactoryWithSize() {
		int listSize = 10;
		RandomListFactory<Integer> randomProductListFactory = new RandomListFactory<>(listSize,
				new RandomIntFactory(5, 10));
		List<Integer> list = randomProductListFactory.create();
		assertTrue(list.size() == listSize);
		assertListContainsValidIntegers(list);
	}

	@Test
	public void testRandomIntListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		RandomListFactory<Integer> randomProductListFactory = new RandomListFactory<>(min, max,
				new RandomIntFactory(5, 10));
		List<Integer> list = randomProductListFactory.create();
		assertTrue(list.size() >= min);
		assertTrue(list.size() <= max);
		assertListContainsValidIntegers(list);
	}

	private void assertListContainsValidIntegers(List<Integer> list) {
		for (Integer integer : list) {
			assertTrue(integer >= 5);
			assertTrue(integer <= 10);
		}
	}

	private void assertListContainsValidProducts(List<Product> list) {
		for (Product product : list) {
			assertTrue(product.getCode().length() > 0);
			assertTrue(product.getName().length() > 0);
			assertTrue(product.getDetails().length() > 0);
			assertTrue(product.getPrice().compareTo(BigDecimal.ZERO) == 1);
		}
	}

}
