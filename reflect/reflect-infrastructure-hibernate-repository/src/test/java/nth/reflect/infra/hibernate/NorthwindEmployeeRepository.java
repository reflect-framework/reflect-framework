package nth.reflect.infra.hibernate;

import nth.reflect.infra.hibernate.HibernateRepository;

public class NorthwindEmployeeRepository extends HibernateRepository<NorthwindEmployee> {

	public NorthwindEmployeeRepository(NorthwindConfig northwindConfig) {
		super(northwindConfig);
	}

}
