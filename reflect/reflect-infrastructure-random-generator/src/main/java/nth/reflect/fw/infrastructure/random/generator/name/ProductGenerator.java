package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class ProductGenerator extends FromListGenerator<Product> {

	public ProductGenerator() {
		super(Resources.productRepository());
	}

}
