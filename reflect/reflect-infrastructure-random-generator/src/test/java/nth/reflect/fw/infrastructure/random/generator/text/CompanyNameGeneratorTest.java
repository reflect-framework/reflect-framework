package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class CompanyNameGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<String> companyNames = Random.companyNameGenerator().generateSet(150);
		assertThat(companyNames, hasSize(greaterThan(100)));
		assertThat(companyNames, hasItem(ProductNameProductDescriptionCompanyNameFile.A_COMPANY_NAME));
	}
	
	@Test
	public void testForCompanyName() {
		Set<String> companyNames = Random.companyNameGenerator().forProductName(ProductNameProductDescriptionCompanyNameFile.A_PRODUCT_NAME).generateSet(20);
		assertThat(companyNames, hasSize(1));
		assertThat(companyNames, hasItem(ProductNameProductDescriptionCompanyNameFile.A_COMPANY_NAME));
	}

}
