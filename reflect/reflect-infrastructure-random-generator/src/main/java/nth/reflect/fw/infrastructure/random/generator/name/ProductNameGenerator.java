package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.List;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class ProductNameGenerator extends FromStringListGenerator {

	
	
	public ProductNameGenerator() {
		super(Repositories.productNameAndDescriptionAndCompanyRepository().getProductNames());
	}

	public ProductNameGenerator(List<String> productNames) {
		super(productNames);
	}

	public ProductNameGenerator forCompanyName(String companyName) {
		return new ProductNameGenerator(Repositories.productNameAndDescriptionAndCompanyRepository().getProductNames(companyName));
	}
}
