package nth.introspect.dataaccess.hibernate.persistenceunit;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
/**
 * Basic J2EE JPA helper class, handles EntityManagerFactory, EntityManager and EntityTransaction
 * <p>
 * Uses a static initializer for the initial EntityManagerFactory creation and holds EntityManager and EntityTransaction in thread local variables.
*/
public class PersistenceUnit {
	private final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	private final ThreadLocal<EntityTransaction> threadEntityTransaction = new ThreadLocal<EntityTransaction>();
	private EntityManagerFactory entityManagerFactory;
	private final PersistenceUnitConfiguration persistenceUnitConfiguration;

	public PersistenceUnit(PersistenceUnitConfiguration persistenceUnitConfiguration){
		this.persistenceUnitConfiguration = persistenceUnitConfiguration;
	}

	/**
	 * Returns the EntityManagerFactory used for this class.
	 * 
	 * @return EntityManagerFactory
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitConfiguration.getPersistenceUnitName(), persistenceUnitConfiguration.getJpaProperties());
		}
		return entityManagerFactory;
	}

	/**
	 * Retrieves the current EntityManager local to the thread. If no Session is open, opens a new EntityManager for the running thread.
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		EntityManager em = threadEntityManager.get();
		if (em == null) {
			em = getEntityManagerFactory().createEntityManager();
			threadEntityManager.set(em);
		}
		return em;
	}

	/**
	 * Closes the EntityManager local to the thread.
	 */
	public void closeEntityManager() {
		EntityManager em = threadEntityManager.get();
		threadEntityManager.set(null);
		if ((null != em) && em.isOpen()) {
			em.close();
		}
	}

	/**
	 * Start a new database entity transaction.
	 */
	public void beginEntityTransaction() {
		EntityTransaction et = threadEntityTransaction.get();
		if (null == et) {
			et = getEntityManager().getTransaction();
			et.begin();
			threadEntityTransaction.set(et);
		}
	}

	/**
	 * Commit the database entity transaction.
	 */
	public void commitEntityTransaction() {
		EntityTransaction et = threadEntityTransaction.get();
		try {
			if ((null != et) && et.isActive()) {
				et.commit();
			}
			threadEntityTransaction.set(null);
		} catch (RollbackException ex) {
			rollbackEntityTransaction();
			throw ex;
		}
	}

	/**
	 * Rollback the database entity transaction.
	 */
	public void rollbackEntityTransaction() {
		EntityTransaction et = threadEntityTransaction.get();
		try {
			threadEntityTransaction.set(null);
			if ((null != et) && et.isActive()) {
				et.rollback();
			}
		} finally {
			closeEntityManager();
		}
	}
	
	public String createSelectQuery(Class<?> domainClass, String whereClause) {
		StringBuffer query = new StringBuffer("select e from ");
		query.append(domainClass.getCanonicalName());
		query.append(" e ");
		if (whereClause!=null && whereClause.length()>0) {
			query.append(whereClause);
		}
		return query.toString();
	}

	public List<?> executeQuery(String queryString) {
		return executeQuery(queryString,null);
	}
	
	public List<?> executeQuery(String queryString, HashMap<String, Object> queryParameters) {
		final Query query = getEntityManager().createQuery(queryString);
		if (queryParameters!=null) {
			for (String parameterName:queryParameters.keySet()) {
				Object parameterValue = queryParameters.get(parameterName);
				query.setParameter(parameterName, parameterValue);				
			}
		}
		return query.getResultList();
	}
}
