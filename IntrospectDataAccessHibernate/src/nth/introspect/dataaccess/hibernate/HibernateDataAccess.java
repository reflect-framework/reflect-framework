package nth.introspect.dataaccess.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import nth.introspect.dataaccess.hibernate.entity.DeletableEntity;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnit;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnitConfiguration;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnitFactory;
import nth.introspect.layer5provider.dataaccess.Criteria;
import nth.introspect.layer5provider.dataaccess.DataAccessProvider;

public abstract class HibernateDataAccess<T> implements DataAccessProvider<T> {

	private PersistenceUnit persistenceUnit;

	public HibernateDataAccess() {
		persistenceUnit = PersistenceUnitFactory.getPersistenceUnit(getPersistenceUnitConfiguration());
	}

	public abstract PersistenceUnitConfiguration getPersistenceUnitConfiguration();

	@Override
	public T getFirst(Criteria critieria) {
		// TODO Auto-generated method stub
		throw new RuntimeException("This method is not supported yet");
	}

	@Override
	public List<T> get(Criteria critieria) {
		// TODO Auto-generated method stub
		throw new RuntimeException("This method is not supported yet");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		StringBuffer query = new StringBuffer("select e from ");
		query.append(getDomainType().getCanonicalName());
		query.append(" e");
		if (DeletableEntity.class.isAssignableFrom(getDomainType())) {
			query.append(" where e.deleted=false");
		}
		return (List<T>) persistenceUnit.executeQuery(query.toString());
	}

	@Override
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
	@Override
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
	@Override
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
