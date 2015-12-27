package nth.introspect.infrastructure.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import nth.introspect.layer4infrastructure.InfrastructureObject;

import org.hibernate.dialect.Dialect;

/**
 * {@link HibernateConfiguration} is a class that holds abstract (read only)
 * properties. It is needed to configure Hibernate (JPA) and can be used as an
 * {@link InfrastructureObject} so that DomainRepositories that extend
 * {@link HibernateRepository} can take this implemented class as a constructor parameter.
 * 
 *  TODO example
 * 
 * @author nilsth
 *
 */
public abstract class HibernateConfiguration {

	private final Properties properties;
	private final List<String> domainPackages;
	private final List<Class<?>> domainClasses;

	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
	private static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
	private static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";

	public HibernateConfiguration() {
		properties = new Properties();
		properties.put(HIBERNATE_CONNECTION_URL, getConnectionUrl());
		properties.put(HIBERNATE_CONNECTION_USERNAME, getUserName());
		properties.put(HIBERNATE_CONNECTION_PASSWORD, getPassword());
		properties.put(HIBERNATE_DIALECT, getDialect().getCanonicalName());
		domainPackages = new ArrayList<String>();
		domainClasses = new ArrayList<Class<?>>();
	}

	public abstract String getConnectionUrl();

	public abstract String getUserName();

	public abstract String getPassword();

	public abstract Class<? extends Dialect> getDialect();

	public HibernateConfiguration addProperty(String key, String value) {
		properties.put(key, value);
		return this;
	}

	public HibernateConfiguration addDomainPackage(String domainPackage) {
		domainPackages.add(domainPackage);
		return this;
	}

	public HibernateConfiguration addDomainClass(Class<?> domainClass) {
		domainClasses.add(domainClass);
		return this;
	}

	public Properties getProperties() {
		return properties;
	}

	public List<String> getDomainPackages() {
		return domainPackages;
	}

	public List<Class<?>> getDomainClasses() {
		return domainClasses;
	}
}
