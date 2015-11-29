package nth.introspect.excel.repository.test;

import nth.introspect.excel.repository.RepositoryForExcel;
import nth.introspect.layer5provider.reflection.ReflectionProvider;

public class PersonExcelRepository extends RepositoryForExcel<Person> {

	public PersonExcelRepository(ReflectionProvider reflectionProvider) {
		super(reflectionProvider);
	}

}
