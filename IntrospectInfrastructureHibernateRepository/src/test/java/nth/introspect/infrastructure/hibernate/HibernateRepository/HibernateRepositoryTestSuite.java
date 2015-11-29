package nth.introspect.infrastructure.hibernate.HibernateRepository;

import junit.framework.TestCase;
import nth.introspect.repository.hibernate.JbdcRepositoryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JbdcRepositoryTest.class, HibernateRepositoryTestSuite.class })
public class HibernateRepositoryTestSuite extends TestCase {

}
