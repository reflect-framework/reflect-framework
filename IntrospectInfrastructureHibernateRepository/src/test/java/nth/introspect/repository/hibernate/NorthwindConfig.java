package nth.introspect.repository.hibernate;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.SQLiteDialect;

public class NorthwindConfig extends HibernateConfiguration {

	static final String NORTHWIND_SL3 = "Northwind.sl3";

	public NorthwindConfig() {
		super();
		addDomainClass(NorthwindEmployee.class);
	}
	
	@Override
	public String getConnectionUrl() {
		
		URL url = this.getClass().getResource(NORTHWIND_SL3);
		File databaseFile;
		try {
			databaseFile = new File(url.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException("Could not find databas file:"+NORTHWIND_SL3,e);
		}
		StringBuilder connectionUrl=new StringBuilder();
		connectionUrl.append("jdbc:sqlite:");
		connectionUrl.append(databaseFile.getAbsolutePath());
		return connectionUrl.toString();
	}

	@Override
	public String getUserName() {
		return "";
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public Class<? extends Dialect> getDialect() {
		return SQLiteDialect.class;
	}

}
