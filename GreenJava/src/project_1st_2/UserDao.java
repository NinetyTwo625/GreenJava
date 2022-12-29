//데이터베이스에 접근하여 관련 작업들을 수행하는 클래스

package project_1st_2;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDao {
	private static UserDao userDao = new UserDao();
	private static OracleDBConnector oracleDBConnector = OracleDBConnector.getInstance();

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	UserData user;
	ArrayList<UserData> userList;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public ArrayList<UserData> select() throws Exception {
		con = oracleDBConnector.getConnection();
		String sql = "SELECT * FROM MEMBER";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		userList = new ArrayList<>();

		while (rs.next()) {
			user = new UserData();
			user.setId(rs.getString("id"));
			user.setPw(rs.getString("pw"));
			user.setName(rs.getString("name"));
			userList.add(user);
		}
		return userList;
	}

	public int insert(String id, String pw, String name) { // 회원가입에 성공하면 발생
		con = oracleDBConnector.getConnection();
		int loginOk;
		try {
			String sql = "INSERT INTO MEMBER VALUES ('" + id + "','" + pw + "','" + name + "')";
			pstmt = con.prepareStatement(sql);
			loginOk = pstmt.executeUpdate();
		} catch (Exception e) {
			return loginOk = 0;
		}
		return loginOk;
	}

	public boolean loginCheck(String id, String pw) throws Exception { // 회원가입한 사람인지 확인
		con = oracleDBConnector.getConnection();
		userList = this.select();
		boolean loginOk = false;
		for (UserData user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw()))
				return loginOk = true;
		}
		return loginOk;
	}

	public void loginSuccess(String id) throws Exception { // 로그인이 성공했을때 발생
		con = oracleDBConnector.getConnection();

		for (UserData user : userList) {
			if (id.equals(user.getId())) {
				String loginName = user.getName();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InetAddress address = InetAddress.getByName("230.0.1.100");
							new Messenger(id, loginName, address, 9999);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				break;
			}
		}
	}

	public int userUpdate(String id, String pw, String cPw, String name) throws Exception { // 회원 정보 수정하기
		con = oracleDBConnector.getConnection();
		int update = 0;
		for (UserData user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw())) {
				String sql = "UPDATE MEMBER SET PW = '" + cPw + "', NAME = '" + name + "' WHERE ID = '" + id + "'";
				pstmt = con.prepareStatement(sql);
				update = pstmt.executeUpdate();
				break;
			}
		}
		return update;
	}

	public int userDelete(String id, String pw) throws Exception { // 회원탈퇴 하기
		con = oracleDBConnector.getConnection();
		int delete = 0;
		for (UserData user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw())) {
				String sql = "DELETE FROM MEMBER WHERE ID = '" + id + "'";

				pstmt = con.prepareStatement(sql);
				delete = pstmt.executeUpdate();
				break;
			}
		}
		return delete;
	}

}
