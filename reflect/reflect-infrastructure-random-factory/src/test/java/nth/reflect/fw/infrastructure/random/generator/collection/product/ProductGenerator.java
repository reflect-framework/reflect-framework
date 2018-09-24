package nth.reflect.fw.infrastructure.randomfactory.product;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.randomfactory.RandomBigDecimalFactory;
import nth.reflect.fw.infrastructure.randomfactory.Factory;
import nth.reflect.fw.infrastructure.randomfactory.RandomLoremIpsumFactory;
import nth.reflect.fw.infrastructure.randomfactory.RandomStringFactory;

public class RandomProductFactory implements Factory<Product> {

	private final RandomStringFactory idFactory;
	private final RandomLoremIpsumFactory nameFactory;
	private final RandomLoremIpsumFactory detailFactory;
	private final RandomBigDecimalFactory priceFactory;
	
	public RandomProductFactory() {
		idFactory=new RandomStringFactory(5, false, true);
		nameFactory=new RandomLoremIpsumFactory(1,4);
		detailFactory=new RandomLoremIpsumFactory(5, 10, 1, 5, 1, 3);
		priceFactory=new RandomBigDecimalFactory(new BigDecimal("10.0"),new BigDecimal("1000.0"));
	}
	
	@Override
	public Product create() {
		Product product=new Product();
		product.setCode(idFactory.create());
		product.setName(nameFactory.create());
		product.setDetails(detailFactory.create());
		product.setPrice(priceFactory.create());
		return product;
	}


}
