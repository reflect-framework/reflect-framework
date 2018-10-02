package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.List;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class ProductDescriptionGenerator extends FromStringListGenerator {

	public ProductDescriptionGenerator() {
		super(Repositories.productNameAndDescriptionAndCompanyRepository().getProductDescriptions());
	}


	public ProductDescriptionGenerator(List<String> productDescriptions) {
		super(productDescriptions);
	}


	public ProductDescriptionGenerator forProductName(String productName) {
		return new ProductDescriptionGenerator(Repositories.productNameAndDescriptionAndCompanyRepository().getProductDescriptions(productName));
	}
}
