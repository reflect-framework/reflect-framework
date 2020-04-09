package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class ProductGenerator extends FromListGenerator<RandomProduct> {

	public ProductGenerator() {
		super(Resources.productRepository());
	}

}
