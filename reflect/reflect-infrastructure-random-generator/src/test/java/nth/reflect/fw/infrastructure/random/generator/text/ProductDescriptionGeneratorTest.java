package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class ProductDescriptionGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<String> productDescriptions = Random.productDescriptionGenerator().generateSet(150);
		assertThat(productDescriptions, hasSize(greaterThan(100)));
		assertThat(productDescriptions, hasItem(ProductNameProductDescriptionCompanyNameFile.A_PRODUCT_DESCRIPTION));
	}
	
	@Test
	public void testForProductName() {
		Set<String> companyNames = Random.productDescriptionGenerator().forProductName(ProductNameProductDescriptionCompanyNameFile.A_PRODUCT_NAME).generateSet(20);
		assertThat(companyNames, hasSize(1));
		assertThat(companyNames, hasItem(ProductNameProductDescriptionCompanyNameFile.A_PRODUCT_DESCRIPTION));
	}

}
