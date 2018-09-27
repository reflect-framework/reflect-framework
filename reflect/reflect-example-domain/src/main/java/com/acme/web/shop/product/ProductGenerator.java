package com.acme.web.shop.product;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BigDecimalGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.RandomLoremIpsumFactory;

public class ProductGenerator implements RandomGenerator<Product> {

	private static final String CODE_12334 = "CODE-12334";//FIXME replace with Random.getFormatFactory("999-AA999")
	private final RandomLoremIpsumFactory nameFactory;
	private final RandomLoremIpsumFactory detailFactory;
	private final BigDecimalGenerator priceFactory;
	
	public ProductGenerator() {
		nameFactory=new RandomLoremIpsumFactory(1,4);
			detailFactory=new RandomLoremIpsumFactory(5, 10, 1, 5, 1, 3);
		priceFactory=new BigDecimalGenerator(new BigDecimal("10.0"),new BigDecimal("1000.0"));
	}
	
	@Override
	public Product generate() {
		Product product=new Product();
		product.setCode(CODE_12334);
		product.setName(nameFactory.generate());
		product.setDetails(detailFactory.generate());
		product.setPrice(priceFactory.generate());
		return product;
	}


}
