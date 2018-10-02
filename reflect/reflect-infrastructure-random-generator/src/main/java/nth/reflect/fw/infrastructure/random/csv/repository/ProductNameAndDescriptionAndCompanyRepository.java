package nth.reflect.fw.infrastructure.random.csv.repository;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.infrastructure.random.csv.CsvRow;

public class ProductNameAndDescriptionAndCompanyRepository extends CsvResourceFileRepository {

	private static final int PRODUCT_NAME_COLUMN = 0;
	private static final int PRODUCT_DESCRIPTION_COLUMN = 1;
	private static final int COMPANY_NAME_COLUMN = 2;

	public ProductNameAndDescriptionAndCompanyRepository() {
		super("ProductNames-ProductDescriptions-CompanyNames.csv");
	}

	public List<String> getProductNames() {
		return getCsvColumn(PRODUCT_NAME_COLUMN);
	}

	public List<String> getProductNames(String companyName) {
		String companyNameToFind = companyName.trim().toLowerCase();
		Predicate<? super CsvRow> filter = row -> row.getCell(COMPANY_NAME_COLUMN).trim().toLowerCase()
				.equals(companyNameToFind);
		return getCsvColumn(PRODUCT_NAME_COLUMN, filter);
	}

	public List<String> getProductDescriptions() {
		return getCsvColumn(PRODUCT_DESCRIPTION_COLUMN);
	}

	public List<String> getProductDescriptions(String productName) {
		String productNameToFind = productName.trim().toLowerCase();
		Predicate<? super CsvRow> filter = row -> row.getCell(PRODUCT_NAME_COLUMN).trim().toLowerCase()
				.equals(productNameToFind);
		return getCsvColumn(PRODUCT_DESCRIPTION_COLUMN, filter);
	}

	public List<String> getCompanyNames() {
		return getCsvColumn(COMPANY_NAME_COLUMN);
	}

	public List<String> getCompanyNames(String productName) {
		String productNameToFind = productName.trim().toLowerCase();
		Predicate<? super CsvRow> filter = row -> row.getCell(PRODUCT_NAME_COLUMN).trim().toLowerCase()
				.equals(productNameToFind);
		return getCsvColumn(COMPANY_NAME_COLUMN, filter);
	}
}
