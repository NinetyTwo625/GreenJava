package project_1st_12;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static DBConnect oracleDBConnector = new DBConnect();
	Connection con = null;

	private DBConnect() {
	}

	public static DBConnect getInstance() {
		if (oracleDBConnector == null) {
			oracleDBConnector = new DBConnect();
		}
		return oracleDBConnector;
	}

	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			con = DriverManager.getConnection(url, "system", "green1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

}