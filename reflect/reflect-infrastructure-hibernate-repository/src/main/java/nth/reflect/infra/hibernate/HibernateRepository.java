package nth.reflect.infra.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.infra.hibernate.entity.DeletableEntity;

/**
 * A {@link InfrastructureObject} to link a {@link DomainObject} type to a
 * database using Hibernate (JPA)
 * 
 * @author nilsth
 *
 *
 * @param <T>
 */

// See also: https://www.baeldung.com/spring-dao-jpa

public abstract class HibernateRepository<T> {

	private final SessionFactory sessionFactory;

	public HibernateRepository(HibernateConfiguration hibernateConfiguration) {
		sessionFactory = createSessionFactory(hibernateConfiguration);
	}

	@SuppressWarnings("deprecation")
	private SessionFactory createSessionFactory(HibernateConfiguration hibernateConfiguration) {
		try {
			Configuration configuration = new Configuration();
			configuration.addProperties(hibernateConfiguration.getProperties());

			for (String domainPackage : hibernateConfiguration.getDomainPackages()) {
				configuration.addPackage(domainPackage);
			}

			for (Class<?> domainClass : hibernateConfiguration.getDomainClasses()) {
				configuration.addAnnotatedClass(domainClass);
			}

			return configuration.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		StringBuffer query = new StringBuffer("select e from ");
		query.append(getDomainType().getCanonicalName());
		query.append(" e");
		if (DeletableEntity.class.isAssignableFrom(getDomainType())) {
			query.append(" where e.deleted=false");
		}
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		List<T> result = session.createQuery(query.toString()).list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public void set(T entity) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void delete(T entity) {
		if (DeletableEntity.class.isAssignableFrom(entity.getClass())) {
			DeletableEntity deletableEntity = (DeletableEntity) entity;
			deletableEntity.setDeleted(true);
			set((T) deletableEntity);// TODO test
		} else {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Class<T> getDomainType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
