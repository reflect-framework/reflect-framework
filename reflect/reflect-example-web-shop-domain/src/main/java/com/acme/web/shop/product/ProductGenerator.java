package com.acme.web.shop.product;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.RandomProduct;
import nth.reflect.fw.infrastructure.random.generator.number.BigDecimalGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.FormatGenerator;

public class ProductGenerator extends RandomGenerator<Product> {

	private final BigDecimalGenerator priceFactory;
	private final nth.reflect.fw.infrastructure.random.generator.name.ProductGenerator productGenerator;
	private final FormatGenerator formatGenerator;
	
	public ProductGenerator() {
		productGenerator = Random.product();
		formatGenerator = Random.format().forFormat("#-######-######");
		priceFactory=new BigDecimalGenerator(new BigDecimal("10.0"),new BigDecimal("1000.0"));
	}
	
	@Override
	public Product generate() {
		RandomProduct randomProduct = productGenerator.generate();
		Product product=new Product();
		
		product.setCode(formatGenerator.generate());
		product.setName(randomProduct.getName());
		product.setDetails(randomProduct.getDescription());
		product.setSupplier(randomProduct.getCompany());
		product.setPrice(priceFactory.generate());
		return product;
	}


}
