package nth.reflect.infra.hibernate;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

public class JbdcRepositoryTest extends TestCase {

	public void testgetAllEmployees() throws ClassNotFoundException,
			SQLException {
		URL url = this.getClass().getResource(NorthwindConfig.NORTHWIND_SL3);
		File databaseFile;
		try {
			databaseFile = new File(url.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException("Could not find databas file:"
					+ NorthwindConfig.NORTHWIND_SL3, e);
		}
		StringBuilder connectionUrl = new StringBuilder();
		connectionUrl.append("jdbc:sqlite:");
		connectionUrl.append(databaseFile.getAbsolutePath());

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(connectionUrl.toString());
		if (conn != null) {
//			System.out.println("Connected to the database");
			DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//			System.out.println("Driver name: " + dm.getDriverName());
//			System.out.println("Driver version: " + dm.getDriverVersion());
//			System.out.println("Product name: " + dm.getDatabaseProductName());
//			System.out.println("Product version: "
//					+ dm.getDatabaseProductVersion());

			String query = "SELECT EmployeeID, LastName, FirstName, Title FROM Employees ";
			Statement stmt = conn.createStatement();
			// Execute a query using Statement object
			ResultSet rs;
			rs = stmt.executeQuery(query);
			// Retrieve data from the returned ResultSet object
			boolean more;
			more = rs.next();
assertTrue(rs.next());
			
//			while (more) {
//				System.out.println("ID: " + rs.getInt("EmployeeId"));
//				System.out.println("Name: " + rs.getString("FirstName") + " "
//						+ rs.getString("LastName"));
//				System.out.println("Title: " + rs.getString("Title"));
//				System.out.println("");
//				more = rs.next();
//			}
			// Close the ResultSet
			rs.close();
			// Close the Statement object
			stmt.close();
			// Close the Connection object
			conn.close();
		}
	}

}
