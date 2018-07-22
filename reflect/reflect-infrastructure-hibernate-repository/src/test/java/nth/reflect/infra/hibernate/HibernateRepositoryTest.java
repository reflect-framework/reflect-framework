package nth.reflect.infra.hibernate;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;

public class HibernateRepositoryTest extends TestCase {

	private static NorthwindEmployeeRepository northwindEmployeeRepository;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit()
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
