package nth.introspect.infrastructure.hibernate;

import nth.introspect.infrastructure.hibernate.HibernateRepository;

public class NorthwindEmployeeRepository extends HibernateRepository<NorthwindEmployee> {

	public NorthwindEmployeeRepository(NorthwindConfig northwindConfig) {
		super(northwindConfig);
	}

}
