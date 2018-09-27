package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class CompanyNameGenerator extends FromStringListGenerator {

	public CompanyNameGenerator() {
		super(new ResourceFile(ProductNameProductDescriptionCompanyNameFile.NAME,
				ProductNameProductDescriptionCompanyNameFile.COMPANY_NAME_COLUMN));
	}

}
