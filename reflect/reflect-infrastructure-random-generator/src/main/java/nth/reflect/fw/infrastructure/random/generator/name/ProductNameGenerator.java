package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.function.Predicate;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile.Row;

public class ProductNameGenerator extends FromStringListGenerator {

	public ProductNameGenerator() {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME,
				ProductNameProductDescriptionCompanyNameFile.PRODUCT_NAME_COLUMN));
	}

	public ProductNameGenerator(Predicate<? super Row> filter) {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME, filter,
				ProductNameProductDescriptionCompanyNameFile.PRODUCT_NAME_COLUMN));
	}

	public ProductNameGenerator forCompanyName(String companyName) {
		String companyNameToFind = companyName.trim().toLowerCase();
		Predicate<? super Row> filter = row -> row
				.getCell(ProductNameProductDescriptionCompanyNameFile.COMPANY_NAME_COLUMN).trim().toLowerCase()
				.equals(companyNameToFind);
		return new ProductNameGenerator(filter);
	}
}
