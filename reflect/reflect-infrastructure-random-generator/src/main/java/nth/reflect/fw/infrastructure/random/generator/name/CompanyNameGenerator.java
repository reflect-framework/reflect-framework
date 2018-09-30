package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.function.Predicate;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile.Row;

public class CompanyNameGenerator extends FromStringListGenerator {

	public CompanyNameGenerator() {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME,
				ProductNameProductDescriptionCompanyNameFile.COMPANY_NAME_COLUMN));
	}
	


	public CompanyNameGenerator(Predicate<? super Row> filter) {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME,
				filter, ProductNameProductDescriptionCompanyNameFile.COMPANY_NAME_COLUMN));
	}



	public CompanyNameGenerator forProductName(String productName) {
		String productNameToFind = productName.trim().toLowerCase();
		Predicate<? super Row> filter = row -> row
				.getCell(ProductNameProductDescriptionCompanyNameFile.PRODUCT_NAME_COLUMN).trim().toLowerCase()
				.equals(productNameToFind);
		return new CompanyNameGenerator(filter);
	}


}
