package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.Product;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.ProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ListGeneratorTest {

	@Test
	public void testRandomProductListFactoryWithSize() {
		int size = 10;
		ListGenerator<Product> productListGenerator = new ListGenerator<>(new ProductGenerator(),size);
		List<Product> list = productListGenerator.generate();
		assertTrue(list.size() == size);
		assertListContainsValidProducts(list);
	}

	@Test
	public void testRandomProductListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		ListGenerator<Product> productListGenerator = new ListGenerator<>(
				new ProductGenerator(),min, max);
		List<Product> list = productListGenerator.generate();
		assertTrue(list.size() >= min);
		assertTrue(list.size() <= max);
		assertListContainsValidProducts(list);
	}

	@Test
	public void testRandomIntListFactoryWithSize() {
		int listSize = 10;
		ListGenerator<Integer> productListGenerator = new ListGenerator<>(new IntGenerator(5, 10),listSize
				);
		List<Integer> list = productListGenerator.generate();
		assertTrue(list.size() == listSize);
		assertListContainsValidIntegers(list);
	}

	@Test
	public void testRandomIntListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		ListGenerator<Integer> productListGenerator = new ListGenerator<>(new IntGenerator(5, 10),min, max);
		List<Integer> list = productListGenerator.generate();
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
