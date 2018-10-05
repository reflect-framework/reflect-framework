package nth.reflect.fw.infrastructure.random.generator.name;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class ProductNameGeneratorTest {

	
	@Test
	public void testForNoParameter() {
		Set<Product> products = Random.productGenerator().generateSet(500);
		assertThat(products, hasSize(greaterThan(110)));
		for (Product product : products) {
			assertValidProduct(product);
		}
	}
	
	private void assertValidProduct(Product product) {
		assertTrue(!product.getName().trim().isEmpty());
		assertTrue(!product.getDescription().trim().isEmpty());
		assertTrue(!product.getCompany().trim().isEmpty());
	}


}
