package nth.introspect.dataaccess.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import nth.introspect.dataaccess.hibernate.entity.DeletableEntity;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnit;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnitConfiguration;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnitFactory;

public abstract class HibernateDataAccess<T>  {

	private PersistenceUnit persistenceUnit;

	public HibernateDataAccess() {
		persistenceUnit = PersistenceUnitFactory.getPersistenceUnit(getPersistenceUnitConfiguration());
	}

	public abstract PersistenceUnitConfiguration getPersistenceUnitConfiguration();


	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		StringBuffer query = new StringBuffer("select e from ");
		query.append(getDomainType().getCanonicalName());
		query.append(" e");
		if (DeletableEntity.class.isAssignableFrom(getDomainType())) {
			query.append(" where e.deleted=false");
		}
		return (List<T>) persistenceUnit.executeQuery(query.toString());
	}

	public void set(T entity) {
		try {
			persistenceUnit.beginEntityTransaction();
			persistenceUnit.getEntityManager().merge(entity);
			persistenceUnit.commitEntityTransaction();
		} finally {
			persistenceUnit.closeEntityManager();
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(T entity) {
		if (DeletableEntity.class.isAssignableFrom(entity.getClass())) {
			DeletableEntity deletableEntity = (DeletableEntity) entity;
			deletableEntity.setDeleted(true);
			set((T) deletableEntity);// TODO test
		} else {
			try {
				persistenceUnit.beginEntityTransaction();
				persistenceUnit.getEntityManager().remove(entity);
				persistenceUnit.commitEntityTransaction();
			} finally {
				persistenceUnit.closeEntityManager();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Class<T> getDomainType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];

	}

	/**
	 * This method must be protected.<br>
	 * External classes may not get access to the persistanceUnit (only via extended {@link DataAccessProvider} classes)
	 * 
	 * @return {@link PersistenceUnit}
	 */
	protected PersistenceUnit getPersistenceUnit() {
		return persistenceUnit;
	}

}
