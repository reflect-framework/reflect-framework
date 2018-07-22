package nth.reflect.infra.excel.repository.test;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.infra.excel.repository.ExcelRepository;

public class PersonExcelRepository extends ExcelRepository<Person> {

	public PersonExcelRepository(ReflectionProvider reflectionProvider) {
		super(reflectionProvider);
	}

}
