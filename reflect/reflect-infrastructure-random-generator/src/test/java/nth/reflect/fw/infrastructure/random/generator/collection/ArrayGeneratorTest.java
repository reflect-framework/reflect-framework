package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProduct;
import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class ArrayGeneratorTest {

	@Test
	public void testRandomProductArrayGeneratorWithSize() {
		int size = 10;
		ArrayGenerator<TestProduct> randomProductArrayGenerator = new ArrayGenerator<>(TestProduct.class,
				new TestProductGenerator(), size);
		TestProduct[] array = randomProductArrayGenerator.generate();
		assertThat(array).hasSize(size);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomProductArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<TestProduct> randomProductArrayGenerator = new ArrayGenerator<>(TestProduct.class,
				new TestProductGenerator(), min, max);
		TestProduct[] array = randomProductArrayGenerator.generate();
		assertThat(array).hasSizeGreaterThanOrEqualTo(min);
		assertThat(array).hasSizeLessThanOrEqualTo(max);
		assertArrayContainsValidProduct(array);
	}

	@Test
	public void testRandomIntArrayGeneratorWithSize() {
		int listSize = 10;
		ArrayGenerator<Integer> randomProductArrayGenerator = new ArrayGenerator<>(Integer.class,
				new IntGenerator(5, 10), listSize);
		Integer[] array = randomProductArrayGenerator.generate();
		assertThat(array).hasSize(listSize);
		assertThat(array).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));
	}

	@Test
	public void testRandomIntArrayGeneratorWithMinMax() {
		int min = 10;
		int max = 50;
		ArrayGenerator<Integer> randomProductArrayGenerator = new ArrayGenerator<>(Integer.class,
				new IntGenerator(5, 10), min, max);
		Integer[] array = randomProductArrayGenerator.generate();
		assertThat(array).hasSizeGreaterThanOrEqualTo(min);
		assertThat(array).hasSizeLessThanOrEqualTo(max);
		assertThat(array).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));
	}

	private void assertArrayContainsValidProduct(TestProduct[] array) {
		for (TestProduct testProduct : array) {
			assertThat(testProduct.getCode()).as("Product Code").isNotBlank();
			assertThat(testProduct.getName()).as("Product Name").isNotBlank();
			assertThat(testProduct.getDetails()).as("Product Details").isNotBlank();
			assertThat(testProduct.getPrice()).as("Price").isGreaterThan(BigDecimal.ZERO);
		}
	}

}
