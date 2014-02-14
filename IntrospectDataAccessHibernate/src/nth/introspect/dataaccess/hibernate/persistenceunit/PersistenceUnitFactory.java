package nth.introspect.dataaccess.hibernate.persistenceunit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PersistenceUnitFactory {
	private static Map<PersistenceUnitConfiguration, PersistenceUnit> persistanceUnits = new HashMap<PersistenceUnitConfiguration, PersistenceUnit>();

	public static PersistenceUnit getPersistenceUnit(PersistenceUnitConfiguration persistenceUnitConfiguration) {
		Set<PersistenceUnitConfiguration> keys = persistanceUnits.keySet();
		PersistenceUnit persistanceUnit = null;
		for (PersistenceUnitConfiguration key:keys) {
			if (key.equals(persistenceUnitConfiguration)) {
				persistanceUnit=persistanceUnits.get(key);
				break;
			}
		}
		
		if (persistanceUnit == null) {
			persistanceUnit = new PersistenceUnit(persistenceUnitConfiguration);
			persistanceUnits.put(persistenceUnitConfiguration, persistanceUnit);
		}
		return persistanceUnit;
	}
}
