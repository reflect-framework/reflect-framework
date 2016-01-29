package nth.introspect.infrastructure.hibernate;

import java.util.List;

import junit.framework.TestCase;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.generic.util.ClassList;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class HibernateRepositoryTest extends TestCase {

	private static NorthwindEmployeeRepository northwindEmployeeRepository;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new IntrospectApplicationForJUnit()
				.addInfrastructureClass(NorthwindConfig.class)
				.addInfrastructureClass(NorthwindEmployeeRepository.class).createContainer();
		northwindEmployeeRepository = container.get(NorthwindEmployeeRepository.class);
	}

	@Test
	public void testGetAllT() {
		List<NorthwindEmployee> employees = northwindEmployeeRepository.getAll();
		assertTrue(employees.size() > 0);
		assertNotNull(employees.get(0).getEmployeeId());
		assertNotNull(employees.get(0).getFirstName());
		assertNotNull(employees.get(0).getLastName());
		assertNotNull(employees.get(0).getTitle());
	}

}
