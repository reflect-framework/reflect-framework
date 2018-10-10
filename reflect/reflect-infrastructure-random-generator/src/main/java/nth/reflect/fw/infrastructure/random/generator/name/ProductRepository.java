package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.resource.ResourceFileRepository;

public class ProductRepository extends ResourceFileRepository<RandomProduct> {

	@Override
	public RandomProduct create(String line) {
		String[] values = line.split(";");
		String name=values[0];
		String description=values[1];
		String company=values[2];
		RandomProduct product=new RandomProduct(name, description, company);
		return product;
	}


}
