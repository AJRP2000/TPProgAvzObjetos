package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static DBManager instancia;
	private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DB_URL = "jdbc:hsqldb:file:sql/testdb;shutdown=true;hsqldb.default_table_type=cached";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	private DBManager() {}
	
	public static DBManager getInstance() {
		if (instancia == null)
			instancia = new DBManager();
		return instancia;
	}
	
	public Connection connect()  {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER);
		}catch (ClassNotFoundException e) {}
		try {
			c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return c;
	}

	public void shutdown() throws Exception {
		Connection c = connect();
		Statement s = c.createStatement();
		s.execute("SHUTDOWN");
		c.close();
	}
}
