package nth.reflect.fw.infrastructure.randomfactory.product;

import java.util.List;

import nth.reflect.fw.infrastructure.randomfactory.RandomListFactory;

public class RandomProductListFactory  {

	private RandomListFactory<Product> randomProductListFactory;

	public RandomProductListFactory() {
		randomProductListFactory = new RandomListFactory<Product>(10, 50, new RandomProductFactory());
	}
	
	public List<Product> createRandomProducts() {
		return randomProductListFactory.create();
	}

}
