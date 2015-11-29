package nth.introspect.repository.hibernate;

public class NorthwindEmployeeRepository extends HibernateRepository<NorthwindEmployee> {

	public NorthwindEmployeeRepository(NorthwindConfig northwindConfig) {
		super(northwindConfig);
	}

}
