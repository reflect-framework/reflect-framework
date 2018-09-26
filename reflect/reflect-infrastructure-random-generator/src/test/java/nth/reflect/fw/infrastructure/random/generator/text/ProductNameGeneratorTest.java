package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class ProductNameGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<String> productNames = Random.productNameGenerator().generateSet(150);
		assertThat(productNames, hasSize(greaterThan(110)));
		assertThat(productNames, hasItem(ProductNameProductDescriptionCompanyNameFile.A_PRODUCT_NAME));
	}
	
	@Test
	public void testForCompanyName() {
		Set<String> productNames = Random.productNameGenerator().forCompanyName(ProductNameProductDescriptionCompanyNameFile.A_COMPANY_NAME).generateSet(20);
		assertThat(productNames, hasSize(greaterThan(2)));
		assertThat(productNames, hasItem(ProductNameProductDescriptionCompanyNameFile.A_PRODUCT_NAME));
	}

}
