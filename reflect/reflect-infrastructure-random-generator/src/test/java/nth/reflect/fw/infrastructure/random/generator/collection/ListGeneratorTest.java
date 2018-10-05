package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.ListGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProduct;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ListGeneratorTest {

	@Test
	public void testRandomProductListFactoryWithSize() {
		int size = 10;
		ListGenerator<TestProduct> productListGenerator = new ListGenerator<>(new TestProductGenerator(),size);
		List<TestProduct> list = productListGenerator.generate();
		assertTrue(list.size() == size);
		assertListContainsValidProducts(list);
	}

	@Test
	public void testRandomProductListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		ListGenerator<TestProduct> productListGenerator = new ListGenerator<>(
				new TestProductGenerator(),min, max);
		List<TestProduct> list = productListGenerator.generate();
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

	private void assertListContainsValidProducts(List<TestProduct> list) {
		for (TestProduct testProduct : list) {
			assertTrue(testProduct.getCode().length() > 0);
			assertTrue(testProduct.getName().length() > 0);
			assertTrue(testProduct.getDetails().length() > 0);
			assertTrue(testProduct.getPrice().compareTo(BigDecimal.ZERO) == 1);
		}
	}

}
