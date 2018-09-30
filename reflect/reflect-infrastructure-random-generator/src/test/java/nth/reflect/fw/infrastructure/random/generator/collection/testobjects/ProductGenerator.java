package nth.reflect.fw.infrastructure.random.generator.collection.testobjects;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class ProductGenerator extends RandomGenerator<Product> {

	@Override
	public Product generate() {
		Product product = new Product();
		product.setCode(Random.formatGenerator().forFormat("AA-999-999-999").generate());
		String productName = Random.productNameGenerator().generate();
		product.setName(productName);
		product.setDetails(Random.productDescriptionGenerator().forProductName(productName).generate());
		product.setCompanyName(Random.companyNameGenerator().forProductName(productName).generate());
		product.setPrice(
				Random.bigDecimalGenerator().forRange(new BigDecimal("10.0"), new BigDecimal("1000.0")).generate());
		return product;
	}

}
