package nth.reflect.fw.infrastructure.random.generator.collection.product;

import java.util.List;

import nth.reflect.fw.infrastructure.random.generator.collection.ListGenerator;

public class ProductListGenerator  {

	private ListGenerator<Product> randomProductListFactory;

	public ProductListGenerator() {
		randomProductListFactory = new ListGenerator<Product>(10, 50, new ProductGenerator());
	}
	
	public List<Product> createRandomProducts() {
		return randomProductListFactory.generate();
	}

}
