package nth.introspect.repository.hibernate;

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
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {
			 
 			@Override
 			public List<Class<?>> getInfrastructureClasses() {
 				return new ClassList(NorthwindConfig.class, NorthwindEmployeeRepository.class);
 			}
 		};
 		DependencyInjectionContainer container = application.createContainer();
 		northwindEmployeeRepository = container.get(NorthwindEmployeeRepository.class);
	}

	@Test
	public void testGetAllT() {
		List<NorthwindEmployee> employees = northwindEmployeeRepository.getAll();
		assertTrue(employees.size()>0);
		assertNotNull(employees.get(0).getEmployeeId());
		assertNotNull(employees.get(0).getFirstName());
		assertNotNull(employees.get(0).getLastName());
		assertNotNull(employees.get(0).getTitle());
	}

	
	@Test
	public void setTest() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void DeleteTest() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void getDomainTypeTest() {
		fail("Not yet implemented"); // TODO
	}

}
