package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProduct;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ListGeneratorTest {
	@Test
	public void testRandomProductListFactoryWithSize() {
		int size = 10;
		ListGenerator<TestProduct> productListGenerator = new ListGenerator<>(new TestProductGenerator(), size);
		List<TestProduct> products = productListGenerator.generate();
		assertThat(products).hasSize(size);
		assertThat(products).allSatisfy(product -> {
			assertThat(product.getCode()).as("Product Code").isNotBlank();
			assertThat(product.getName()).as("Product Name").isNotBlank();
			assertThat(product.getDetails()).as("Product Details").isNotBlank();
			assertThat(product.getPrice()).as("Product Price").isGreaterThan(BigDecimal.ZERO);
		});
	}

	@Test
	public void testRandomProductListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		ListGenerator<TestProduct> productListGenerator = new ListGenerator<>(new TestProductGenerator(), min, max);
		List<TestProduct> products = productListGenerator.generate();
		assertThat(products).hasSizeGreaterThanOrEqualTo(min).hasSizeLessThanOrEqualTo(max);
		assertThat(products).allSatisfy(product -> {
			assertThat(product.getCode()).as("Product Code").isNotBlank();
			assertThat(product.getName()).as("Product Name").isNotBlank();
			assertThat(product.getDetails()).as("Product Details").isNotBlank();
			assertThat(product.getPrice()).as("Product Price").isGreaterThan(BigDecimal.ZERO);
		});
	}

	@Test
	public void testRandomIntListFactoryWithSize() {
		int listSize = 10;
		ListGenerator<Integer> intListGenerator = new ListGenerator<>(new IntGenerator(5, 10), listSize);
		List<Integer> ints = intListGenerator.generate();
		assertThat(ints).hasSize(listSize);
		assertThat(ints).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));
	}

	@Test
	public void testRandomIntListFactoryWithMinMax() {
		int min = 10;
		int max = 50;
		ListGenerator<Integer> intListGenerator = new ListGenerator<>(new IntGenerator(5, 10), min, max);
		List<Integer> ints = intListGenerator.generate();
		assertThat(ints).hasSizeGreaterThanOrEqualTo(min).hasSizeLessThanOrEqualTo(max);
		assertThat(ints).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));
	}

}
