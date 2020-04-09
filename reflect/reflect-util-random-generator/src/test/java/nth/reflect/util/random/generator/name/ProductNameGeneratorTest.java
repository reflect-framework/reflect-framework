package nth.reflect.util.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.name.RandomProduct;

public class ProductNameGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<RandomProduct> products = Random.product().generateSet(500);
		assertThat(products).size().isGreaterThan(110);
		for (RandomProduct product : products) {
			assertValidProduct(product);
		}
	}

	private void assertValidProduct(RandomProduct product) {
		assertThat(product.getName()).as("Product name").isNotBlank();
		assertThat(product.getDescription()).as("Product description").isNotBlank();
		assertThat(product.getCompany()).as("Product company").isNotBlank();
	}

}
