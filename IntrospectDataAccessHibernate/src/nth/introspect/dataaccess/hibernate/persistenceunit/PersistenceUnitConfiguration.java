package nth.introspect.dataaccess.hibernate.persistenceunit;

import java.util.HashMap;
import java.util.Map;

public class PersistenceUnitConfiguration {
	private static final String INTROSPECT_PERSISTENCE_UNIT = "introspectPersistenceUnit";
	private final Map<String, String> jpaProperties;
	private final String persistenceUnitName;

	public PersistenceUnitConfiguration() {
		this(INTROSPECT_PERSISTENCE_UNIT, new HashMap<String, String>());
	}

	public PersistenceUnitConfiguration(String persistenceUnit) {
		this(persistenceUnit, new HashMap<String, String>());
	}

	public PersistenceUnitConfiguration(String persistenceUnitName, Map<String, String> jpaProperties) {
		this.jpaProperties = jpaProperties;
		this.persistenceUnitName = persistenceUnitName;
	}

	public Map<String, String> getJpaProperties() {
		return jpaProperties;
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (!(that instanceof PersistenceUnitConfiguration)) {
			return false;
		}
		PersistenceUnitConfiguration persistenceUnitConfiguration = (PersistenceUnitConfiguration) that;
		if (!persistenceUnitName.equals(persistenceUnitConfiguration.getPersistenceUnitName())) {
			return false;
		}
		return jpaProperties.equals(persistenceUnitConfiguration.getJpaProperties());
	}

}
