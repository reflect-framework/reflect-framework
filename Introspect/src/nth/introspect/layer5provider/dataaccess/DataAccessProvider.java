package nth.introspect.layer5provider.dataaccess;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;

/**
 * @deprecated Use a BackendServiceClass instead. See
 *             {@link IntrospectApplication#getInfrastructureClasses()} on how
 *             to register back end service classes and see
 *             {@link IntrospectContainer} on how to inject them into the
 *             constructors of FrontendServiceObjects
 * The DataAccessProvider Interface should be implemented by all classes that
 * access a data source (being a database, a xml or soap service, etc..)<br>
 * Note that all parameters needed to make a connection should be stored via the
 * constructor.<br>
 * <br>
 * {@link DataAccessProvider} objects must be obtained via
 * {@link DataAccessProvider#getDataAccess(Class)}<br>
 * The {@link DataAccessProvider} must be obtained via
 * {@link Introspect#getDataAccessProvider(Class)}<br>
 * <br>
 * An example:<br>
 * <br>
 * HibernateDataAccess:<br>
 * &nbsp;&nbsp;public abstract class HibernateDataAccess<T> implements
 * DataAccessProvider<T>{<br>
 * &nbsp;&nbsp;public abstract HibernateConfig getHibernateConfig();<br>
 * &nbsp;&nbsp;public HibernateConfig() {HibernateConfig
 * hibernateConfig=getHibernateConfig(); {@literal //TODO} init hibernate}<br>
 * &nbsp;&nbsp;{@literal //TODO} DataAccessProvider implement methods with type
 * {@literal<T>}<br>
 * }<br>
 * <br>
 * MyProjectDataAccess<br>
 * public abstract class MyProjectDataAccess<T> extends HibernateDataAccess<T> {<br>
 * &nbsp;&nbsp;{@literal @Override} <br>
 * &nbsp;&nbsp;public HibernateConfig getHibernateConfig() { // TODO } }<br>
 * <br>
 * PersonDataAccess<br>
 * &nbsp;&nbsp;public class PersonDataAccess extends MyProjectDataAccess<Person>
 * {{@literal //TODO} <br>
 * 
 * 
 */

public interface DataAccessProvider<T> {

	public T getFirst(Criteria critieria);

	public List<T> get(Criteria critieria);

	public List<T> getAll();

	public void set(T domainObject);

	public void delete(T domainObject);

	public Class<? extends T> getDomainType();
}
