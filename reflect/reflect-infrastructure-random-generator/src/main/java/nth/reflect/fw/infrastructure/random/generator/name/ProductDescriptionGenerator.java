package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.function.Predicate;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile.Row;

public class ProductDescriptionGenerator extends FromStringListGenerator {

	public ProductDescriptionGenerator() {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME,
				ProductNameProductDescriptionCompanyNameFile.PRODUCT_DESCRIPTION_COLUMN));
	}

	public ProductDescriptionGenerator(Predicate<? super Row> filter) {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME, filter,
				ProductNameProductDescriptionCompanyNameFile.PRODUCT_DESCRIPTION_COLUMN));
	}

	public ProductDescriptionGenerator forProductName(String productName) {
		String productNameToFind = productName.trim().toLowerCase();
		Predicate<? super Row> filter = row -> row
				.getCell(ProductNameProductDescriptionCompanyNameFile.PRODUCT_NAME_COLUMN).trim().toLowerCase()
				.equals(productNameToFind);
		return new ProductDescriptionGenerator(filter);
	}
}
