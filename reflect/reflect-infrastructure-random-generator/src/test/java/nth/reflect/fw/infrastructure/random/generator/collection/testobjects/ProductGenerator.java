package nth.reflect.fw.infrastructure.random.generator.collection.testobjects;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BigDecimalGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.RandomLoremIpsumFactory;

public class ProductGenerator extends RandomGenerator<Product> {
/**
 * FIXME replace {@link #CODE_12345} with a {@link RandomGenerator} (e.g. a FormatGenerator)
 */
private static final String CODE_12345 = "CODE-12345";
	private final RandomLoremIpsumFactory nameFactory;
	private final RandomLoremIpsumFactory detailFactory;
	private final BigDecimalGenerator priceFactory;
	
	public ProductGenerator() {
//		idFactory=new StringFactory(5, false, true);
		nameFactory=new RandomLoremIpsumFactory(1,4);
		detailFactory=new RandomLoremIpsumFactory(5, 10, 1, 5, 1, 3);
		priceFactory=new BigDecimalGenerator(new BigDecimal("10.0"),new BigDecimal("1000.0"));
	}
	
	@Override
	public Product generate() {
		Product product=new Product();
		product.setCode(CODE_12345);
		product.setName(nameFactory.generate());
		product.setDetails(detailFactory.generate());
		product.setPrice(priceFactory.generate());
		return product;
	}


}
