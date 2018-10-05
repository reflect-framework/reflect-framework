package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.resource.ResourceFileRepository;

public class ProductRepository extends ResourceFileRepository<Product> {

	@Override
	public Product create(String line) {
		String[] values = line.split(";");
		String name=values[0];
		String description=values[1];
		String company=values[2];
		Product product=new Product(name, description, company);
		return product;
	}


}
