package nth.introspect.infrastructure.hibernate;

public class NorthwindEmployeeRepository extends HibernateRepository<NorthwindEmployee> {

	public NorthwindEmployeeRepository(NorthwindConfig northwindConfig) {
		super(northwindConfig);
	}

}
