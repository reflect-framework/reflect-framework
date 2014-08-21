package nth.introspect.dataaccess.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.Introspect;
import nth.introspect.provider.dataaccess.Criteria;
import nth.introspect.provider.dataaccess.DataAccessProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;

public abstract  class SqlDataAccess implements DataAccessProvider<Object> {
	protected Connection connection;

	public abstract SqlDatabaseConfig getSqlDatabaseConfig() ;

	public Statement executeSQL(String sql) throws Exception {
		try {
			Statement statement = createConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.execute(sql);
			if (sql.startsWith("select")) {
			} else {
			}
			// if (!connection.getAutoCommit()) {
			// // commit manualy because auto commit is disabled
			// connection.commit();
			// }
			return statement;
		} catch (SQLException e) {
			throw new Exception("Failed to execute SQL command:\r" + sql, e);
		}
	}

	protected Connection createConnection() {
		if (connection == null) {
			SqlDatabaseConfig sqlDatabaseConfig= getSqlDatabaseConfig();
			try {
				Class.forName(sqlDatabaseConfig.getDriverClass().getCanonicalName());// load driver class
				connection = DriverManager.getConnection(sqlDatabaseConfig.getUrl(), sqlDatabaseConfig.getUserName(), sqlDatabaseConfig.getPassword());
			} catch (SQLException exception) {
				throw new IllegalStateException("Failed to connect to the database using the update user. Please contact your DBA.", exception);
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("Could not find database driver: " + sqlDatabaseConfig.getDriverClass().getCanonicalName(), e);
			}
		}
		return connection;
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException dbNotClosingSQLException) {
				System.err.println("Failed to close JDBC connection " + dbNotClosingSQLException);
			}
		}
	}

	// private String getResultString(String sql) throws Exception {
	// Statement statement = executeSQL(sql);
	// ResultSet resultSet = statement.getResultSet();
	// resultSet.next();
	// return resultSet.getString(1);
	// }

	public List<?> getResultList(String sql, Class<?> domainClass) throws Exception {
		Statement statement = executeSQL(sql);
		ResultSet resultSet = statement.getResultSet();
		List<Object> results = new ArrayList<Object>();

		DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
		Map<String, PropertyInfo> propertyInfos = new HashMap<String, PropertyInfo>();
		ResultSetMetaData meta = resultSet.getMetaData();
		int numColumns = meta.getColumnCount();
		for (int columnNr = 1; columnNr < numColumns + 1; columnNr++) {
			String columnName = meta.getColumnName(columnNr);
			for (PropertyInfo propertyInfo : domainInfoProvider.getPropertyInfos(domainClass)) {
				if (propertyInfo.getName().equalsIgnoreCase(columnName)) {
					propertyInfos.put(columnName, propertyInfo);
					break;
				}
			}
		}

		while (resultSet.next()) {
			Object domainObject = domainClass.newInstance();
			for (int columnNr = 1; columnNr < numColumns + 1; columnNr++) {
				String columnName = meta.getColumnName(columnNr);
				Object value = resultSet.getObject(columnNr);
				PropertyInfo propertyInfo = propertyInfos.get(columnName);
				if (propertyInfo == null) {
					throw new RuntimeException("Could not find property: " + columnName + " in class: " + domainClass.getCanonicalName());
				}
				try {
					propertyInfo.setValue(domainObject, value);
					// } catch (IllegalArgumentException e) {
					// throw new RuntimeException("Property type of property: " + domainClass.getCanonicalName()+"."+propertyInfo.getName()+" must be of type"+value.getClass().getCanonicalName());
				} catch (Exception e) {
					throw new RuntimeException("Could not set property: " + domainClass.getCanonicalName() + "." + propertyInfo.getName(), e);
				}
			}
			results.add(domainObject);
		}
		return results;
	}

	public Map<String, Object> getResultMap(String sql) throws Exception {
		Statement statement = executeSQL(sql);
		ResultSet resultSet = statement.getResultSet();
		Map<String, Object> results = new HashMap<String, Object>();
		while (resultSet.next()) {
			results.put(resultSet.getString(1), resultSet.getObject(2));
		}
		return results;
	}

	@Override
	public Object getFirst(Criteria critieria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> get(Criteria critieria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(Object domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getDomainType() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
