package nth.introspect.dataaccess.sql;


public class SqlDatabaseConfig {
	private final String url;
	private final String userName;
	private final String password;
	private final Class<?> driverClass;
	
	public SqlDatabaseConfig(Class<?> driverClass, String url, String userName, String password) {
		super();
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Class<?> getDriverClass() {
		return driverClass;
	}
	
	
	
	
}
