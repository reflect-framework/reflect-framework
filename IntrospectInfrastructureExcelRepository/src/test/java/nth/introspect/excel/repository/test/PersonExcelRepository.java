package nth.introspect.excel.repository.test;

import nth.introspect.excel.repository.ExcelRepository;
import nth.introspect.layer5provider.reflection.ReflectionProvider;

public class PersonExcelRepository extends ExcelRepository<Person> {

	public PersonExcelRepository(ReflectionProvider reflectionProvider) {
		super(reflectionProvider);
	}

}
