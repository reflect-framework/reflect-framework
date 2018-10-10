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
		Set<RandomProduct> products = Random.product().generateSet(500);
		assertThat(products, hasSize(greaterThan(110)));
		for (RandomProduct product : products) {
			assertValidProduct(product);
		}
	}
	
	private void assertValidProduct(RandomProduct product) {
		assertTrue(!product.getName().trim().isEmpty());
		assertTrue(!product.getDescription().trim().isEmpty());
		assertTrue(!product.getCompany().trim().isEmpty());
	}


}
