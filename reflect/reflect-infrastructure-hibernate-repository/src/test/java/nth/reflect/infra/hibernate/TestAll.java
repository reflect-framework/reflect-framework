package nth.reflect.infra.hibernate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.TestCase;

@RunWith(Suite.class)
@SuiteClasses({ HibernateRepositoryTest.class })
public class TestAll extends TestCase {

}
