package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.List;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class CompanyNameGenerator extends FromStringListGenerator {

	public CompanyNameGenerator() {
		super(Repositories.productNameAndDescriptionAndCompanyRepository().getCompanyNames());
	}
	
	public CompanyNameGenerator(List<String> companyNames) {
		super(companyNames); 
	}

	public CompanyNameGenerator forProductName(String productName) {
		return new CompanyNameGenerator(Repositories.productNameAndDescriptionAndCompanyRepository().getCompanyNames(productName));
	}


}
