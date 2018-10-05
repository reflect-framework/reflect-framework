package nth.reflect.fw.infrastructure.random.generator.collection.testobjects;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class TestProductGenerator extends RandomGenerator<TestProduct> {

	@Override
	public TestProduct generate() {
		TestProduct testProduct = new TestProduct();
		testProduct.setCode(Random.formatGenerator().forFormat("AA-999-999-999").generate());
		nth.reflect.fw.infrastructure.random.generator.name.Product randomProduct = Random.productGenerator().generate();
		testProduct.setName(randomProduct.getName());
		testProduct.setDetails(randomProduct.getDescription());
		testProduct.setCompanyName(randomProduct.getCompany());
		testProduct.setPrice(
				Random.bigDecimalGenerator().forRange(new BigDecimal("10.0"), new BigDecimal("1000.0")).generate());
		return testProduct;
	}
}
