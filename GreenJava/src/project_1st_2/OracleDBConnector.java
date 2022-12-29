//오라클 DB 연결

package project_1st_2;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDBConnector {
	private static OracleDBConnector oracleDBConnector = new OracleDBConnector();
	Connection con = null;

	private OracleDBConnector() {
	}

	public static OracleDBConnector getInstance() {
		if (oracleDBConnector == null) {
			oracleDBConnector = new OracleDBConnector();
		}
		return oracleDBConnector;
	}

	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			con = DriverManager.getConnection(url, "c##green", "green1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

}
